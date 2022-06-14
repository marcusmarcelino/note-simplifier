package br.com.apinotesimplifier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.apinotesimplifier.models.Sale;
import br.com.apinotesimplifier.models.SellItem;

public interface SellItemRepository extends JpaRepository<SellItem, Long> {
  Optional<List<SellItem>> findByIdSale(Sale idSale);

  @Query("SELECT sellItem FROM SellItem sellItem WHERE sellItem.idSale.id = :idSale")
  Page<SellItem> findItemsByIdSale(Long idSale, Pageable pageable);
}
