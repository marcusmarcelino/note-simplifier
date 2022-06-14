package br.com.apinotesimplifier.interfaces;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.apinotesimplifier.dto.SaleDTO;
import br.com.apinotesimplifier.dto.SaleFormDTO;
import br.com.apinotesimplifier.models.Sale;

public interface SaleService {
  SaleDTO save(Sale sale) throws Exception;

  Sale findById(Long id);

  SaleDTO findSaleDTOById(Long id);

  Page<Sale> findByDateOfSale(LocalDate dateOfSale, Pageable pageable);

  SaleDTO update(SaleFormDTO saleForm) throws Exception;

  void delete(Long id);

  Page<SaleDTO> findAll(Pageable pageable);

  Page<SaleDTO> findAllSales(Pageable pageable);
}
