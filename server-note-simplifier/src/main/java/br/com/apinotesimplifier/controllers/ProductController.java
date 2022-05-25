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

import br.com.apinotesimplifier.interfaces.ProductService;
import br.com.apinotesimplifier.models.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping("")
  public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
    return ResponseEntity.ok().body(productService.save(product));
  }

  @PutMapping("")
  public ResponseEntity<Product> update(@RequestBody Product product) {
    return ResponseEntity.ok().body(productService.update(product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Valid> delete(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findById(@PathVariable Long id) {
    return ResponseEntity.ok().body(productService.findById(id));
  }

  @GetMapping("/ean/{eanPrincipal}")
  public ResponseEntity<Product> findByEanPrincipal(@PathVariable String eanPrincipal) {
    return ResponseEntity.ok().body(productService.findByEanMain(eanPrincipal));
  }

  @GetMapping("")
  public ResponseEntity<Page<Product>> getAll(Pageable pageable) {
    return ResponseEntity.ok().body(productService.findAll(pageable));
  }
}
