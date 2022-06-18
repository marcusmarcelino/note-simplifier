package br.com.apinotesimplifier.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.apinotesimplifier.enums.ProgressStatus;
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
@Table(name = "services_provideds")
public class ServiceProvided {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Muitos serviços prestados para um cliente
  @NotNull(message = "A client must be assigned to this service provided!")
  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_client", referencedColumnName = "id")
  private User idClient;

  // Muitos serviços prestados por um profissional
  @NotNull(message = "A professional must be assigned to this service provided!")
  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_professional", referencedColumnName = "id")
  private User idProfessional;

  @Column(name = "service_description")
  private String serviceDescription;

  @NotNull(message = "Required field!")
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "id_payment_for_service_provided", referencedColumnName = "id")
  private PaymentForServiceProvided idPaymentForServiceProvided;

  @Column(name = "service_date")
  private LocalDate serviceDate;

  @Column(name = "vl_total")
  private BigDecimal vlTotal;

  @Column(name = "situation")
  private ProgressStatus situation;
}
