package br.com.apinotesimplifier.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProvidedFormDTO {
  private Long id;
  private String serviceDescription;
  private LocalDate serviceDate;
  private BigDecimal vlTotal;
}