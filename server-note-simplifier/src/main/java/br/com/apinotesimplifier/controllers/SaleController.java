package br.com.apinotesimplifier.controllers;

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

import br.com.apinotesimplifier.dto.SaleDTO;
import br.com.apinotesimplifier.dto.SaleFormDTO;
import br.com.apinotesimplifier.interfaces.SaleService;
import br.com.apinotesimplifier.models.Sale;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
  @Autowired
  private SaleService service;

  @PostMapping("")
  public ResponseEntity<SaleDTO> save(@Valid @RequestBody Sale sale) throws Exception {
    return ResponseEntity.ok().body(service.save(sale));
  }

  @PutMapping("")
  public ResponseEntity<SaleDTO> update(@RequestBody SaleFormDTO saleForm) throws Exception {
    return ResponseEntity.ok().body(service.update(saleForm));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SaleDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.findSaleDTOById(id));
  }

  @GetMapping("")
  public ResponseEntity<Page<SaleDTO>> getAll(Pageable pageable) {
    return ResponseEntity.ok().body(service.findAllSales(pageable));
  }
}
