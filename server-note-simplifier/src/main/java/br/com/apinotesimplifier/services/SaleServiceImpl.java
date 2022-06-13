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
import br.com.apinotesimplifier.enums.AccountstatusUser;
import br.com.apinotesimplifier.enums.SituationPaymentSale;
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

    if (!client.getAccountStatus().equals(AccountstatusUser.active)) {
      throw new Exception("The user is listed as: " + client.getAccountStatus().toString());
    }

    sale.getSellItems().forEach((item) -> item.setIdSale(sale));

    sale.setIdClient(client);
    sale.setIdSeller(seller);
    sale.getIdSalePayment().setIdSale(sale);
    sale.getIdSalePayment().setSituation(SituationPaymentSale.PROCESSING);
    return new SaleDTO(saleRepository.save(sale));
  }

  @Override
  public Sale findById(Long id) {
    Optional<Sale> sale = saleRepository.findById(id);
    return sale.orElseThrow(() -> new ResourceNotFoundException("Service provided not found"));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<SaleDTO> findAll(Pageable pageable) {
    Page<Sale> result = saleRepository.findAll(pageable);
    return result.map(sale -> new SaleDTO(sale));
  }

  @Override
  public Page<Sale> findByDateOfSale(LocalDate dateOfSale, Pageable pageable) {
    Page<Sale> page = saleRepository.findByDateOfSale(dateOfSale, pageable);
    return page;
  }

  @Override
  public SaleDTO update(SaleFormDTO saleForm) throws Exception {
    Sale saleFound = findById(saleForm.getId());

    if (saleFound.getIdSalePayment().getSituation().equals(SituationPaymentSale.CONCLUDED)) {
      throw new Exception(
          "The sale cannot be changed as it is listed as: " + saleFound.getIdSalePayment().getSituation().toString());
    }

    if (saleForm.getAddedItems().size() > 0) {
      saleForm.getAddedItems().forEach((itemAdded) -> {
        Stream<SellItem> items = saleFound.getSellItems().stream()
            .filter((item) -> item.getIdProduct().getEanMain().equals(itemAdded.getIdProduct().getEanMain()));
        if (items.count() == 0) {
          saleFound.getSellItems().addAll(saleForm.getAddedItems());
        }
      });
    }

    if (saleForm.getRemovedItems().size() > 0) {
      for (Integer i = 0; i < saleForm.getRemovedItems().size(); i++) {
        if (saleForm.getRemovedItems().get(i).equals(saleFound.getSellItems().get(i).getId())) {
          saleFound.getSellItems().remove(saleFound.getSellItems().get(i));
        }
      }
      sellItemRepository.deleteAllById(saleForm.getRemovedItems());
    }

    if (saleForm.getUpdatedItems().size() > 0) {
      for (Integer i = 0; i < saleForm.getUpdatedItems().size(); i++) {
        if (saleForm.getUpdatedItems().get(i).getId().equals(saleFound.getSellItems().get(i).getId())) {
          SellItem itemForm = saleForm.getUpdatedItems().get(i);
          Product product = itemForm.getIdProduct();
          saleFound.getSellItems().get(i).setQuantityItems(itemForm.getQuantityItems());
          saleFound.getSellItems().get(i).setVlUnitary(product.getVlUnitary());
          saleFound.getSellItems().get(i).setVlTotal(
              product.getVlUnitary().multiply(BigDecimal.valueOf(itemForm.getQuantityItems())));
        }
      }
    }

    saleFound.setVlTotal(this.calculateTotal(saleFound.getSellItems()));
    saleFound.getIdSalePayment().setTotal(this.calculateTotal(saleFound.getSellItems()));
    saleFound.getIdSalePayment().setInstallmentForm(saleForm.getInstallmentForm());
    saleFound.getIdSalePayment().setPaymentMethods(saleForm.getPaymentMethods());

    Sale saleUpdated = saleRepository.save(saleFound);

    return new SaleDTO(saleUpdated);
  }

  @Override
  public void delete(Long id) {
    Sale sale = findById(id);
    saleRepository.delete(sale);
  }

  @Override
  public SaleDTO findSaleDTOById(Long id) {
    Sale sale = findById(id);
    return new SaleDTO(sale);
  }

  @Override
  public Page<SaleDTO> findAllSales(Pageable pageable) {
    Page<Sale> page = saleRepository.findAll(pageable);
    saleRepository.findAllSales(page.stream().collect(Collectors.toList()));
    return page.map(sale -> new SaleDTO(sale));
  }

  public BigDecimal calculateTotal(List<SellItem> items) {
    BigDecimal vlTotal = BigDecimal.ZERO;
    items.forEach((item) -> vlTotal.add(item.getVlTotal()));
    return vlTotal;
  }
}
