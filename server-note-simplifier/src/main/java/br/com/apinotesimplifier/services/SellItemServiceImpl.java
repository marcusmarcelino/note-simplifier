package br.com.apinotesimplifier.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.dto.SellItemDTO;
import br.com.apinotesimplifier.error.ResourceNotFoundException;
import br.com.apinotesimplifier.interfaces.ProductService;
import br.com.apinotesimplifier.interfaces.SaleService;
import br.com.apinotesimplifier.interfaces.SellItemService;
import br.com.apinotesimplifier.models.Product;
import br.com.apinotesimplifier.models.Sale;
import br.com.apinotesimplifier.models.SellItem;
import br.com.apinotesimplifier.repository.SellItemRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class SellItemServiceImpl implements SellItemService {
  @Autowired
  private SellItemRepository sellItemRepository;
  @Autowired
  private SaleService saleService;
  @Autowired
  private ProductService productService;

  @Override
  public SellItem save(SellItem sellItem) {
    return sellItemRepository.save(sellItem);
  }

  @Override
  public SellItem saveItem(SellItem sellItem) {
    Sale sale = saleService.findById(sellItem.getIdSale().getId());
    Product product = productService.findById(sellItem.getIdProduct().getId());
    sellItem.setIdSale(sale);
    sellItem.setIdProduct(product);
    return save(sellItem);
  }

  @Override
  public List<SellItem> saveItems(List<SellItem> items) {
    Sale sale = saleService.findById(items.get(0).getIdSale().getId());
    items.forEach((item) -> {
      Product product = productService.findById(item.getIdProduct().getId());
      item.setIdProduct(product);
      item.setIdSale(sale);
    });
    List<SellItem> itemsCreated = saveAll(items);
    sale.getSellItems().addAll(itemsCreated);
    return itemsCreated;
  }

  @Override
  public List<SellItem> saveAll(List<SellItem> sellItems) {
    return sellItemRepository.saveAll(sellItems);
  }

  @Transactional(readOnly = true)
  @Override
  public SellItem findById(Long id) {
    Optional<SellItem> sellItem = sellItemRepository.findById(id);
    return sellItem.orElseThrow(() -> new ResourceNotFoundException("Item not found in database!"));
  }

  @Transactional(readOnly = true)
  @Override
  public SellItemDTO findItemDTOById(Long id) {
    Optional<SellItem> sellItem = sellItemRepository.findById(id);
    return new SellItemDTO(sellItem.orElseThrow(() -> new ResourceNotFoundException("Item not found in database!")));
  }

  @Override
  public SellItem update(SellItem sellItem) {
    findById(sellItem.getId());
    return save(sellItem);
  }

  @Override
  public void delete(Long id) {
    SellItem item = findById(id);
    sellItemRepository.delete(item);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<SellItemDTO> findByIdSalePageable(Long idSale, Pageable pageable) {
    saleService.findById(idSale);
    Page<SellItem> items = sellItemRepository.findItemsByIdSale(idSale, pageable);
    return items.map((item) -> new SellItemDTO(item));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<SellItemDTO> pageable(Pageable pageable) {
    Page<SellItem> itemsPageable = sellItemRepository.findAll(pageable);
    return itemsPageable.map((item) -> new SellItemDTO(item));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<SellItemDTO> findAllByIdSale(Long idSale, Pageable pageable) {
    Sale sale = saleService.findById(idSale);
    Page<SellItem> items = sellItemRepository.findByIdSale(sale, pageable);
    return items.map(sellItem -> new SellItemDTO(sellItem));
  }
}
