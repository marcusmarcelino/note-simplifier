package br.com.apinotesimplifier.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.apinotesimplifier.dto.SellItemDTO;
import br.com.apinotesimplifier.models.SellItem;

public interface SellItemService {
  SellItem save(SellItem sellItem);

  SellItem saveItem(SellItem sellItem);

  List<SellItem> saveItems(List<SellItem> sellItems);

  List<SellItem> saveAll(List<SellItem> sellItems);

  SellItem findById(Long id);

  SellItemDTO findItemDTOById(Long id);

  SellItem update(SellItem sellItem);

  void delete(Long id);

  Page<SellItemDTO> findByIdSalePageable(Long idSale, Pageable pageable);

  Page<SellItemDTO> pageable(Pageable pageable);

  Page<SellItemDTO> findAllByIdSale(Long idSale, Pageable pageable);
}
