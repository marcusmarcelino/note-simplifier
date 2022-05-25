package br.com.apinotesimplifier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apinotesimplifier.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findByEanMain(String eanPrincipal);
}
