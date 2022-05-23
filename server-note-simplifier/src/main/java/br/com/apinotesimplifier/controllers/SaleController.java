package br.com.apinotesimplifier.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apinotesimplifier.dto.SaleDTO;
import br.com.apinotesimplifier.interfaces.SaleService;
import br.com.apinotesimplifier.models.Sale;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
  @Autowired
  private SaleService saleService;

  @PostMapping("")
  public ResponseEntity<Sale> save(@Valid @RequestBody Sale sale) {
    return ResponseEntity.ok().body(saleService.save(sale));
  }

  @PutMapping("")
  public ResponseEntity<Sale> update(@Valid @RequestBody Sale sale) {
    return ResponseEntity.ok().body(saleService.update(sale));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    saleService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<SaleDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok().body(saleService.findSaleDTOById(id));
  }

  @GetMapping("")
  public ResponseEntity<Page<SaleDTO>> getAll(Pageable pageable) {
    return ResponseEntity.ok().body(saleService.findAllSales(pageable));
  }
}
