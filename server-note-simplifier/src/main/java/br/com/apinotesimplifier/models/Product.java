package br.com.apinotesimplifier.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, name = "id", nullable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "type") // UN | CAIXA | FARDO
  private String type;

  @Column(name = "ean", unique = true)
  private String ean;

  @Column(name = "ean_main", unique = true)
  private String eanMain;

  @Column(name = "vl_unitary")
  private BigDecimal vlUnitary;

  @Column(name = "quantity_per_box")
  private Integer quantityPerBox;

  public Product(Long id) {
    this.id = id;
  }
}
