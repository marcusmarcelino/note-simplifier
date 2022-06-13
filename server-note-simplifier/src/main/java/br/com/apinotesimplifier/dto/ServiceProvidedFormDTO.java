package br.com.apinotesimplifier.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ServiceProvidedFormDTO {
  private Long id;
  private String serviceDescription;
  private LocalDate serviceDate;
  private BigDecimal vlTotal;
}