package br.com.apinotesimplifier.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.apinotesimplifier.models.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
  Page<Sale> findByDateOfSale(LocalDate dateOfSale, Pageable pageable);

  @Query("SELECT obj FROM Sale obj JOIN FETCH obj.sellItems WHERE obj IN :sales")
  List<Sale> findAllSales(List<Sale> sales);
}
