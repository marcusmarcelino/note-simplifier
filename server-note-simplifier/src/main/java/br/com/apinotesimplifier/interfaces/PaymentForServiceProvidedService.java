package br.com.apinotesimplifier.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.apinotesimplifier.models.PaymentForServiceProvided;

public interface PaymentForServiceProvidedService {
  PaymentForServiceProvided savePaymentForServiceProvided(PaymentForServiceProvided paymentForServiceProvided);

  PaymentForServiceProvided getPaymentForServiceProvidedById(Long id);

  List<PaymentForServiceProvided> getPaymentForServiceProvidedByIdServiceProvided(Long idServiceProvided);

  List<PaymentForServiceProvided> getPaymentForServiceProvidedByPayday(LocalDate payday);

  List<PaymentForServiceProvided> getPaymentsForServiceProvided();
}
