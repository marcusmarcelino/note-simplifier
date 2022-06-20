package br.com.apinotesimplifier.dto;

import java.math.BigDecimal;

import br.com.apinotesimplifier.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFormDTO {
  private Long id;
  private String name;
  private String description;
  private String type;
  private BigDecimal vlUnitary;
  private Integer quantityPerBox;

  public ProductFormDTO(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
    this.type = product.getType().toString();
    this.vlUnitary = product.getVlUnitary();
    this.quantityPerBox = product.getQuantityPerBox();
  }
}