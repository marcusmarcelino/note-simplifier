package br.com.apinotesimplifier.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.dto.SaleDTO;
import br.com.apinotesimplifier.error.ResourceNotFoundException;
import br.com.apinotesimplifier.interfaces.PaymentMethodService;
import br.com.apinotesimplifier.interfaces.ProductService;
import br.com.apinotesimplifier.interfaces.SaleService;
import br.com.apinotesimplifier.models.PaymentMethod;
import br.com.apinotesimplifier.models.Product;
import br.com.apinotesimplifier.models.Sale;
import br.com.apinotesimplifier.models.SellItem;
import br.com.apinotesimplifier.models.User;
import br.com.apinotesimplifier.repository.SaleRepository;
import br.com.apinotesimplifier.repository.SellItemRepository;
import br.com.apinotesimplifier.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {
  @Autowired
  private SaleRepository saleRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PaymentMethodService paymentMethodService;
  @Autowired
  private ProductService productService;
  @Autowired
  private SellItemRepository sellItemRepository;

  @Override
  public Sale save(Sale sale) {
    Optional<User> seller = userRepository.findById(sale.getIdSeller().getId());
    Optional<User> client = userRepository.findById(sale.getIdClient().getId());

    List<PaymentMethod> payMethods = new ArrayList<>();
    sale.getIdSalePayment().getPaymentMethods().forEach((payMeth) -> {
      PaymentMethod paymentMethod = paymentMethodService.findById(payMeth.getId());
      payMethods.add(paymentMethod);
    });

    seller.ifPresentOrElse((sell) -> sale.setIdSeller(sell),
        () -> new ResourceNotFoundException("Seller not found!"));
    client.ifPresentOrElse((cli) -> sale.setIdClient(cli),
        () -> new ResourceNotFoundException("Client not found!"));

    sale.getIdSalePayment().setPaymentMethods(payMethods);
    List<SellItem> items = sale.getSellItems();
    sale.setSellItems(new ArrayList<>());
    Sale saleCreated = saleRepository.save(sale);

    items.forEach((item) -> {
      Product product = productService.findById(item.getIdProduct().getId());
      item.setIdProduct(product);
      item.setIdSale(saleCreated);
      item.setVlUnitary(product.getVlUnitary());
      item.setVlTotal(product.getVlUnitary().multiply(BigDecimal.valueOf(item.getQuantityItems())));
    });
    List<SellItem> itemsCreated = sellItemRepository.saveAll(items);

    saleCreated.getIdSalePayment().setIdSale(saleCreated);
    saleCreated.setSellItems(itemsCreated);

    return saleCreated;
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
  public List<Sale> findByDateOfSale(LocalDate dateOfSale) {
    Optional<List<Sale>> sales = saleRepository.findByDateOfSale(dateOfSale);
    return sales.orElse(new ArrayList<>());
  }

  @Override
  public Sale update(Sale sale) {
    findById(sale.getId());
    return saleRepository.save(sale);
  }

  @Override
  public void delete(Long id) {
    Sale sale = findById(id);
    saleRepository.delete(sale);
  }

  @Override
  public SaleDTO findSaleDTOById(Long id) {
    Optional<Sale> sale = saleRepository.findById(id);
    return new SaleDTO(sale.orElseThrow(() -> new ResourceNotFoundException("Sale not found!")));
  }

  @Override
  public Page<SaleDTO> findAllSales(Pageable pageable) {
    Page<Sale> page = saleRepository.findAll(pageable);
    saleRepository.findAllSales(page.stream().collect(Collectors.toList()));
    return page.map(sale -> new SaleDTO(sale));
  }
}
