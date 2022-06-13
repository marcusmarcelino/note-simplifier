package br.com.apinotesimplifier.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.apinotesimplifier.interfaces.PaymentMethodService;
import br.com.apinotesimplifier.models.PaymentMethod;

@RestController
@RequestMapping("/api/paymentmethods")
public class PaymentMethodController {
  @Autowired
  private PaymentMethodService service;

  @PostMapping("")
  public ResponseEntity<PaymentMethod> save(@Valid @RequestBody PaymentMethod paymentMethod) {
    return ResponseEntity.ok().body(service.save(paymentMethod));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaymentMethod> getById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.findById(id));
  }

  @GetMapping("")
  public ResponseEntity<List<PaymentMethod>> getAll() {
    return ResponseEntity.ok().body(service.findAll());
  }

  @PutMapping("")
  public ResponseEntity<PaymentMethod> update(@RequestBody PaymentMethod paymentMethod) {
    return ResponseEntity.ok().body(service.update(paymentMethod));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
