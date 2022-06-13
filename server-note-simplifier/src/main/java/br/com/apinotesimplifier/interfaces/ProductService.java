package br.com.apinotesimplifier.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.apinotesimplifier.dto.ProductFormDTO;
import br.com.apinotesimplifier.dto.UpdateEanProductFormDTO;
import br.com.apinotesimplifier.models.Product;

public interface ProductService {
  Product save(Product product);

  Product update(ProductFormDTO product);

  Product updateEans(UpdateEanProductFormDTO eanObject);

  void delete(Long id);

  Product findById(Long id);

  Product findByEanMain(String eanMain);

  Page<Product> findAll(Pageable pageable);
}
