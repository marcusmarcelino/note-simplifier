package br.com.apinotesimplifier.dto;

import java.io.Serializable;
import java.math.BigDecimal;

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
  private BigDecimal vlUnitary;
  private BigDecimal vlTotal;

  @JsonIgnore
  private Long idSale;

  public SellItemDTO(SellItem sellItem) {
    this.id = sellItem.getId();
    this.idProduct = sellItem.getIdProduct().getId();
    this.productName = sellItem.getIdProduct().getName();
    this.quantityItems = sellItem.getQuantityItems();
    this.vlUnitary = sellItem.getVlUnitary();
    this.vlTotal = sellItem.getVlTotal();
  }
}
