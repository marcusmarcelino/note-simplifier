package br.com.apinotesimplifier.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apinotesimplifier.dto.SellItemDTO;
import br.com.apinotesimplifier.interfaces.SellItemService;
import br.com.apinotesimplifier.models.SellItem;

@RestController
@RequestMapping("/api/sale")
public class SellItemController {
  @Autowired
  private SellItemService service;

  @PostMapping("/items")
  public ResponseEntity<SellItem> save(@Valid @RequestBody SellItem sellItem) {
    return ResponseEntity.ok().body(service.saveItem(sellItem));
  }

  @PostMapping("/items/list")
  public ResponseEntity<List<SellItem>> saveAll(@Valid @RequestBody List<SellItem> sellItems) {
    return ResponseEntity.ok().body(service.saveItems(sellItems));
  }

  @PutMapping("/items")
  public ResponseEntity<SellItem> update(@RequestBody SellItem sellItem) {
    return ResponseEntity.ok().body(service.update(sellItem));
  }

  @GetMapping("/items/{id}")
  public ResponseEntity<SellItemDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.findItemDTOById(id));
  }

  @GetMapping("/{id}/items")
  public ResponseEntity<Page<SellItemDTO>> getItemsByIdSalePageable(@PathVariable Long id, Pageable pageable) {
    return ResponseEntity.ok().body(service.findByIdSalePageable(id, pageable));
  }

  @GetMapping("/items/pageable")
  public ResponseEntity<Page<SellItemDTO>> getItemsPageable(Pageable pageable) {
    return ResponseEntity.ok().body(service.pageable(pageable));
  }

  @GetMapping("/{id}/list")
  public ResponseEntity<Page<SellItemDTO>> getAllItemsByIdSale(@PathVariable Long id, Pageable pageable) {
    return ResponseEntity.ok().body(service.findAllByIdSale(id, pageable));
  }

  @DeleteMapping("/items/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
