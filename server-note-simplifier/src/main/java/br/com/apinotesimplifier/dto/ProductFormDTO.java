package br.com.apinotesimplifier.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductFormDTO {
  private Long id;
  private String name;
  private String description;
  private String type;
  private BigDecimal vlUnitary;
  private Integer quantityPerBox;
}