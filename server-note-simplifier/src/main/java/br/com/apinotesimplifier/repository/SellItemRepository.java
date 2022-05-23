package br.com.apinotesimplifier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apinotesimplifier.models.Sale;
import br.com.apinotesimplifier.models.SellItem;

public interface SellItemRepository extends JpaRepository<SellItem, Long> {
  Optional<List<SellItem>> findByIdSale(Sale idSale);
}
