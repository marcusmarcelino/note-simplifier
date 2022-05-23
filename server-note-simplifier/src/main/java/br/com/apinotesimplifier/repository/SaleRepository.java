package br.com.apinotesimplifier.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.apinotesimplifier.models.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
  Optional<List<Sale>> findByDateOfSale(LocalDate dateOfSale);

  @Query("SELECT obj FROM Sale obj JOIN FETCH obj.sellItems")
  List<Sale> findSalesRemovedErrorN1NotPageable();

  @Query("SELECT obj FROM Sale obj JOIN FETCH obj.sellItems WHERE obj IN :sales")
  List<Sale> findAllSales(List<Sale> sales);
}
