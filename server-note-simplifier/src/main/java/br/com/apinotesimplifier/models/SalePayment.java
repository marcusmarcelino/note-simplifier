package br.com.apinotesimplifier.models;

import java.math.BigDecimal;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.apinotesimplifier.enums.PaymentSituation;
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
@Table(name = "sale_payments")
public class SalePayment {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "payment_methods")
  @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
  private List<PaymentMethod> paymentMethods = new ArrayList<>();

  @Column(name = "Installment_form")
  private Integer installmentForm;

  @Column(name = "total")
  private BigDecimal total;

  @Column(name = "situation")
  private PaymentSituation situation;

  @Column(name = "payday", nullable = true)
  private LocalDate payday;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_sale", referencedColumnName = "id")
  private Sale idSale;

  @Column(name = "date")
  private LocalDate date;
}
