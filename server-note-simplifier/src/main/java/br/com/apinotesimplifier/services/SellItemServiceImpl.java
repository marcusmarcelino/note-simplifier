package br.com.apinotesimplifier.services;

import java.util.ArrayList;
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
  public SellItem saveWithIds(SellItemDTO sellItem) {
    Sale sale = saleService.findById(sellItem.getIdSale());
    Product product = productService.findById(sellItem.getIdProduct());
    SellItem newSellItem = new SellItem();
    newSellItem.setIdSale(sale);
    newSellItem.setIdProduct(product);
    newSellItem.setQuantityItems(sellItem.getQuantityItems());
    return save(newSellItem);
  }

  @Override
  public List<SellItem> saveAllWithIds(List<SellItemDTO> items) {
    Sale sale = saleService.findById(items.get(0).getIdSale());
    List<SellItem> sellItems = new ArrayList<>();
    for (SellItemDTO item : items) {
      Product product = productService.findById(item.getIdProduct());
      SellItem sellItem = new SellItem();
      sellItem.setIdProduct(product);
      sellItem.setIdSale(sale);
      sellItem.setQuantityItems(item.getQuantityItems());
      sellItems.add(sellItem);
    }
    return sellItemRepository.saveAll(sellItems);
  }

  @Override
  public List<SellItem> saveAll(List<SellItem> sellItems) {
    return sellItemRepository.saveAll(sellItems);
  }

  @Override
  public SellItem findById(Long id) {
    Optional<SellItem> sellItem = sellItemRepository.findById(id);
    return sellItem.orElseThrow(() -> new ResourceNotFoundException("Item not found in database!"));
  }

  @Override
  public SellItem update(SellItem sellItem) {
    findById(sellItem.getId());
    return sellItemRepository.save(sellItem);
  }

  @Override
  public void delete(Long id) {
    SellItem item = findById(id);
    sellItemRepository.delete(item);
  }

  @Override
  public List<SellItem> findByIdSale(Long idSale) {
    Sale sale = saleService.findById(idSale);
    Optional<List<SellItem>> items = sellItemRepository.findByIdSale(sale);
    return items.orElse(new ArrayList<>());
  }

  @Override
  public Page<SellItem> findByIdSalePageable(Pageable pageable) {
    Page<SellItem> page = sellItemRepository.findAll(pageable);
    return page;
  }
}
