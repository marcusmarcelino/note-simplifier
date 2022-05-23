package br.com.apinotesimplifier.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "sales")
public class Sale {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, name = "id", nullable = false)
  private Long id;

  @NotNull(message = "A customer must be assigned to this sale!")
  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_client", referencedColumnName = "id")
  private User idClient;

  @NotNull(message = "A seller must be assigned to this sale!")
  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_seller", referencedColumnName = "id")
  private User idSeller;

  @NotNull(message = "Required field!")
  @Column(name = "sell_items")
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "idSale")
  private List<SellItem> sellItems = new ArrayList<>();

  @NotNull(message = "Required field!")
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_sales_payment", referencedColumnName = "id")
  private SalePayment idSalePayment;

  @NotNull(message = "Required field!")
  @Column(name = "dateOfSale")
  private LocalDate dateOfSale;
}
