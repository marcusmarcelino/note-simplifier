package br.com.apinotesimplifier.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.apinotesimplifier.models.Product;

public interface ProductService {
  Product save(Product product);

  Product update(Product product);

  void delete(Long id);

  Product findById(Long id);

  Product findByEanPrincipal(String eanPrincipal);

  Page<Product> findAll(Pageable pageable);
}
