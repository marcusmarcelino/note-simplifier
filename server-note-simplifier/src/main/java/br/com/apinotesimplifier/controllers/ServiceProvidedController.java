package br.com.apinotesimplifier.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.apinotesimplifier.dto.ServiceProvidedDTO;
import br.com.apinotesimplifier.dto.ServiceProvidedFormDTO;
import br.com.apinotesimplifier.interfaces.ServiceProvidedService;
import br.com.apinotesimplifier.models.ServiceProvided;

@RestController
@RequestMapping("/api/servicesprovided")
public class ServiceProvidedController {
  @Autowired
  private ServiceProvidedService service;

  @PostMapping("")
  public ResponseEntity<ServiceProvidedDTO> save(@Valid @RequestBody ServiceProvided serviceProvided) {
    return ResponseEntity.ok().body(service.save(serviceProvided));
  }

  @PutMapping("")
  public ResponseEntity<ServiceProvidedDTO> updated(@RequestBody ServiceProvidedFormDTO serviceProvided)
      throws Exception {
    return ResponseEntity.ok().body(service.update(serviceProvided));
  }

  @PutMapping("/endservice/{id}")
  public ResponseEntity<ServiceProvidedDTO> endService(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.endService(id));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ServiceProvidedDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.findServiceProvidedDTOById(id));
  }

  @GetMapping("/client/{id}")
  public ResponseEntity<Page<ServiceProvidedDTO>> getByIdClient(@PathVariable Long id, Pageable pageable) {
    return ResponseEntity.ok().body(service.findByIdClient(id, pageable));
  }

  @GetMapping("/professional/{id}")
  public ResponseEntity<Page<ServiceProvidedDTO>> getByidProfessional(@PathVariable Long id, Pageable pageable) {
    return ResponseEntity.ok().body(service.findByIdProfessional(id, pageable));
  }

  @GetMapping("")
  public ResponseEntity<Page<ServiceProvidedDTO>> getAll(Pageable pageable) {
    return ResponseEntity.ok().body(service.findAll(pageable));
  }

  @GetMapping("/date")
  public ResponseEntity<Page<ServiceProvidedDTO>> getByServiceDate(@RequestParam String serviceDate, Pageable pageable)
      throws Exception {
    LocalDate data;
    try {
      data = LocalDate.parse(serviceDate, DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (Exception e) {
      throw new Exception("Erro no parâmetro data" + e.getMessage());
    }
    return ResponseEntity.ok().body(service.findByServiceDate(data, pageable));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
