package br.com.apinotesimplifier.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.apinotesimplifier.models.PaymentMethod;
import br.com.apinotesimplifier.models.SalePayment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalePaymentDTO {
  private Long id;
  private List<PaymentMethod> paymentMethods = new ArrayList<>();
  private Integer installmentForm;
  private BigDecimal total;
  private String situation;
  private LocalDate payday;
  private LocalDate date;

  public SalePaymentDTO(SalePayment salePayment) {
    this.id = salePayment.getId();
    this.paymentMethods = salePayment.getPaymentMethods();
    this.installmentForm = salePayment.getInstallmentForm();
    this.total = salePayment.getTotal();
    this.situation = salePayment.getSituation().toString();
    this.payday = salePayment.getPayday();
    this.date = salePayment.getDate();
  }
}
