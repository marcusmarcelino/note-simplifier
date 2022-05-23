package br.com.apinotesimplifier.interfaces;

import java.util.List;

import br.com.apinotesimplifier.models.PaymentMethod;

public interface PaymentMethodService {
  PaymentMethod save(PaymentMethod paymentMethod);

  PaymentMethod findById(Long id);

  PaymentMethod findByType(String type);

  PaymentMethod update(PaymentMethod paymentMethod);

  void delete(Long id);

  List<PaymentMethod> findAll();
}
