package br.com.apinotesimplifier.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.apinotesimplifier.dto.SaleDTO;
import br.com.apinotesimplifier.models.Sale;

public interface SaleService {
  Sale save(Sale sale);

  Sale saveWithIds(Sale sale, Long idSeller, Long idClient, List<Long> paymentMethods);

  Sale findById(Long id);

  SaleDTO findSaleDTOById(Long id);

  List<Sale> findByDateOfSale(LocalDate dataOfSale);

  Sale update(Sale sale);

  void delete(Long id);

  Page<SaleDTO> findAll(Pageable pageable);

  Page<SaleDTO> findAllSales(Pageable pageable);
}
