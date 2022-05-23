package br.com.apinotesimplifier.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.apinotesimplifier.models.SellItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellItemDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private Long idProduct;
  private String productName;
  private Integer quantityItems;
  @JsonIgnore
  private Long idSale;

  public SellItemDTO() {
  }

  public SellItemDTO(Long id, Long idProduct, String productName, Integer quantityItems) {
    this.id = id;
    this.idProduct = idProduct;
    this.productName = productName;
    this.quantityItems = quantityItems;
  }

  public SellItemDTO(Long id, Long idProduct, String productName, Integer quantityItems, Long idSale) {
    this.id = id;
    this.idProduct = idProduct;
    this.productName = productName;
    this.quantityItems = quantityItems;
    this.idSale = idSale;
  }

  public SellItemDTO(SellItem sellItem) {
    this.id = sellItem.getId();
    this.idProduct = sellItem.getIdProduct().getId();
    this.productName = sellItem.getIdProduct().getName();
    this.quantityItems = sellItem.getQuantityItems();
  }
}
