package br.com.apinotesimplifier.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.apinotesimplifier.models.ServiceProvided;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProvidedDTO {
  private Long id;
  private Long idClient;
  private Long idProfessional;
  private String serviceDescription;
  private String situation;
  private Long idPaymentForServiceProvided;
  private LocalDate serviceDate;
  private BigDecimal vlTotal;

  public ServiceProvidedDTO(ServiceProvided serviceProvided) {
    this.id = serviceProvided.getId();
    this.idClient = serviceProvided.getIdClient().getId();
    this.idProfessional = serviceProvided.getIdProfessional().getId();
    this.serviceDescription = serviceProvided.getServiceDescription();
    this.situation = serviceProvided.getSituation().toString();
    this.idPaymentForServiceProvided = serviceProvided.getIdPaymentForServiceProvided().getId();
    this.serviceDate = serviceProvided.getServiceDate();
    this.vlTotal = serviceProvided.getVlTotal();
  }
}
