package br.com.apinotesimplifier.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.apinotesimplifier.models.SalePayment;

public interface SalePaymentService {
  SalePayment saveSalePayment(SalePayment salePayment);

  SalePayment getSalePaymentById(Long id);

  List<SalePayment> getSalePayment(Long idSale);

  List<SalePayment> getSalesPaymentByPayday(LocalDate payday);

  List<SalePayment> getSalesPayment();
}
