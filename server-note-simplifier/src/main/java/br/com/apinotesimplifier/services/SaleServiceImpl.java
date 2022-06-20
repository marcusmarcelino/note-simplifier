package br.com.apinotesimplifier.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.dto.SaleDTO;
import br.com.apinotesimplifier.dto.SaleFormDTO;
import br.com.apinotesimplifier.dto.SaleListDTO;
import br.com.apinotesimplifier.enums.AccountStatus;
import br.com.apinotesimplifier.enums.ProgressStatus;
import br.com.apinotesimplifier.enums.PaymentSituation;
import br.com.apinotesimplifier.error.ResourceNotFoundException;
import br.com.apinotesimplifier.interfaces.SaleService;
import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.Product;
import br.com.apinotesimplifier.models.Sale;
import br.com.apinotesimplifier.models.SellItem;
import br.com.apinotesimplifier.models.User;
import br.com.apinotesimplifier.repository.SaleRepository;
import br.com.apinotesimplifier.repository.SellItemRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class SaleServiceImpl implements SaleService {
  @Autowired
  private SaleRepository saleRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private SellItemRepository sellItemRepository;

  @Override
  public SaleDTO save(Sale sale) throws Exception {
    User client = userService.findById(sale.getIdClient().getId());
    User seller = userService.findById(sale.getIdSeller().getId());

    if (!client.getAccountStatus().equals(AccountStatus.active)) {
      throw new Exception("The user is listed as: " + client.getAccountStatus().toString());
    }

    sale.getSellItems().forEach((item) -> item.setIdSale(sale));

    sale.setIdClient(client);
    sale.setIdSeller(seller);
    sale.getIdSalePayment().setIdSale(sale);
    sale.getIdSalePayment().setDate(LocalDate.now());
    sale.getIdSalePayment().setSituation(PaymentSituation.PROCESSING);
    sale.getIdSalePayment().setTotal(this.calculateTotal(sale.getSellItems()));
    sale.setVlTotal(this.calculateTotal(sale.getSellItems()));
    sale.setSituation(ProgressStatus.IN_PROGRESS);

    return new SaleDTO(saleRepository.save(sale));
  }

  @Transactional(readOnly = true)
  @Override
  public Sale findById(Long id) {
    Optional<Sale> sale = saleRepository.findById(id);
    return sale.orElseThrow(() -> new ResourceNotFoundException("Sale not found in the database!"));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Sale> findByDateOfSale(LocalDate dateOfSale, Pageable pageable) {
    Page<Sale> page = saleRepository.findByDateOfSale(dateOfSale, pageable);
    return page;
  }

  @Transactional(rollbackFor = { Exception.class })
  @Override
  public SaleDTO update(SaleFormDTO saleForm) throws Exception {
    try {
      Sale saleFound = findById(saleForm.getId());

      if (saleFound.getSituation().equals(ProgressStatus.FINALIZED)) {
        String status = saleFound.getSituation().toString();
        throw new Exception("The sale cannot be changed as it is listed as: " + status);
      }

      if (saleForm.getAddedItems().size() > 0) {
        for (SellItem itemAdded : saleForm.getAddedItems()) {
          Stream<SellItem> sellItems = saleFound.getSellItems().stream();
          String eanMain = itemAdded.getIdProduct().getEanMain();
          Long count = sellItems.filter((item) -> item.getIdProduct().getEanMain().equals(eanMain)).count();

          if (count == 0) {
            itemAdded.setIdSale(saleFound);
            saleFound.getSellItems().add(itemAdded);
          }
        }
      }

      if (saleForm.getRemovedItems().size() > 0) {
        for (SellItem sellItem : saleForm.getRemovedItems()) {
          Integer indexOf = saleFound.getSellItems().indexOf(sellItem);
          if (indexOf > -1) {
            Optional<SellItem> removedItem = sellItemRepository.findById(saleFound.getSellItems().get(indexOf).getId());
            saleFound.getSellItems().remove(saleFound.getSellItems().get(indexOf));
            removedItem.ifPresentOrElse((item) -> sellItemRepository.delete(removedItem.get()),
                () -> new ResourceNotFoundException("Item not found in the database!"));
          }
        }
      }

      if (saleForm.getUpdatedItems().size() > 0) {
        for (SellItem sellItem : saleForm.getUpdatedItems()) {
          Integer indexOf = saleFound.getSellItems().indexOf(sellItem);
          if (indexOf > -1) {
            Product product = sellItem.getIdProduct();
            saleFound.getSellItems().get(indexOf).setQuantityItems(sellItem.getQuantityItems());
            saleFound.getSellItems().get(indexOf).setVlUnitary(product.getVlUnitary());
          }
        }
      }

      saleFound.setVlTotal(this.calculateTotal(saleFound.getSellItems()));
      saleFound.getIdSalePayment().setTotal(this.calculateTotal(saleFound.getSellItems()));

      if (saleForm.getInstallmentForm() != null) {
        saleFound.getIdSalePayment().setInstallmentForm(saleForm.getInstallmentForm());
      }

      if (saleForm.getPaymentMethods().size() > 0) {
        saleFound.getIdSalePayment().setPaymentMethods(saleForm.getPaymentMethods());
      }

      return new SaleDTO(saleRepository.save(saleFound));
    } catch (Exception e) {
      throw new Exception("Error in updated sale: " + e);
    }
  }

  @Override
  public void delete(Long id) {
    Sale sale = findById(id);
    saleRepository.delete(sale);
  }

  @Transactional(readOnly = true)
  @Override
  public SaleDTO findSaleDTOById(Long id) {
    Sale sale = findById(id);
    return new SaleDTO(sale);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<SaleListDTO> findAllSales(Pageable pageable) {
    Page<Sale> page = saleRepository.findAll(pageable);
    saleRepository.findAllSales(page.stream().collect(Collectors.toList()));
    return page.map(sale -> new SaleListDTO(sale));
  }

  private BigDecimal calculateTotal(List<SellItem> items) {
    BigDecimal vlTotal = items.stream().map(SellItem::getVlTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    return vlTotal;
  }
}
