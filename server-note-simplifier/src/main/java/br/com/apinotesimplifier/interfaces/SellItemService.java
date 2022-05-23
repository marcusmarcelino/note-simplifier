package br.com.apinotesimplifier.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.apinotesimplifier.dto.SellItemDTO;
import br.com.apinotesimplifier.models.SellItem;

public interface SellItemService {
  SellItem save(SellItem sellItem);

  SellItem saveWithIds(SellItemDTO sellItem);

  List<SellItem> saveAllWithIds(List<SellItemDTO> sellItems);

  List<SellItem> saveAll(List<SellItem> sellItems);

  SellItem findById(Long id);

  SellItem update(SellItem sellItem);

  void delete(Long id);

  List<SellItem> findByIdSale(Long idSale);

  Page<SellItem> findByIdSalePageable(Pageable pageable);
}
