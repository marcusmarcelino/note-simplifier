package br.com.apinotesimplifier.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public Product update(Product product) {
    findById(product.getId());
    return productRepository.save(product);
  }

  @Override
  public void delete(Long id) {
    Product product = findById(id);
    productRepository.delete(product);
  }

  @Override
  public Product findById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    return product.orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
  }

  @Override
  public Product findByEanMain(String eanPrincipal) {
    Optional<Product> product = productRepository.findByEanMain(eanPrincipal);
    return product.orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
  }

  @Override
  public Page<Product> findAll(Pageable pageable) {
    Page<Product> page = productRepository.findAll(pageable);
    return page;
  }
}
