package br.com.apinotesimplifier.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.dto.ProductFormDTO;
import br.com.apinotesimplifier.dto.UpdateEanProductFormDTO;
import br.com.apinotesimplifier.enums.ProductType;
import br.com.apinotesimplifier.error.ResourceNotFoundException;
import br.com.apinotesimplifier.interfaces.ProductService;
import br.com.apinotesimplifier.models.Product;
import br.com.apinotesimplifier.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product update(ProductFormDTO dto) {
    Product product = findById(dto.getId());
    product.setName(dto.getName());
    product.setType(ProductType.valueOf(dto.getType()));
    product.setVlUnitary(dto.getVlUnitary());
    product.setDescription(dto.getDescription());
    product.setQuantityPerBox(dto.getQuantityPerBox());
    return product;
  }

  @Override
  public Product updateEans(UpdateEanProductFormDTO eanObject) {
    Product product = findById(eanObject.getId());

    if (eanObject.getEan() != null) {
      if (!eanObject.getEan().isEmpty() && !eanObject.getEan().isBlank()) {
        product.setEan(eanObject.getEan());
      }
    }

    if (eanObject.getEanMain() != null) {
      if (!eanObject.getEanMain().isEmpty() && !eanObject.getEanMain().isBlank()) {
        product.setEanMain(eanObject.getEanMain());
      }
    }

    return product;
  }

  @Override
  public void delete(Long id) {
    Product product = findById(id);
    productRepository.delete(product);
  }

  @Transactional(readOnly = true)
  @Override
  public Product findById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    return product.orElseThrow(() -> new ResourceNotFoundException("Product not found in the database!"));
  }

  @Transactional(readOnly = true)
  @Override
  public Product findByEanMain(String eanPrincipal) {
    Optional<Product> product = productRepository.findByEanMain(eanPrincipal);
    return product.orElseThrow(() -> new ResourceNotFoundException("Product not found in the database!"));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<Product> findAll(Pageable pageable) {
    Page<Product> page = productRepository.findAll(pageable);
    return page;
  }
}
