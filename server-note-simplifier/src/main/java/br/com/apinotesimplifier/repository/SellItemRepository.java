package br.com.apinotesimplifier.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.apinotesimplifier.models.Sale;
import br.com.apinotesimplifier.models.SellItem;

public interface SellItemRepository extends JpaRepository<SellItem, Long> {
  Page<SellItem> findByIdSale(Sale idSale, Pageable pageable);

  @Query("SELECT sellItem FROM SellItem sellItem WHERE sellItem.idSale.id = :idSale")
  Page<SellItem> findItemsByIdSale(Long idSale, Pageable pageable);
}
