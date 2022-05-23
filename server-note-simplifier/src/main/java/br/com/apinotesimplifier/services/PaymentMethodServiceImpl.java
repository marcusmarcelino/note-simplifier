package br.com.apinotesimplifier.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.apinotesimplifier.error.ResourceNotFoundException;
import br.com.apinotesimplifier.interfaces.PaymentMethodService;
import br.com.apinotesimplifier.models.PaymentMethod;
import br.com.apinotesimplifier.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

  @Autowired
  private PaymentMethodRepository paymentMethodRepository;

  @Override
  public PaymentMethod save(PaymentMethod paymentMethod) {
    Optional<PaymentMethod> payMeth = paymentMethodRepository.findByType(paymentMethod.getType());
    if (payMeth.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Existing payment method");
    } else {
      return paymentMethodRepository.save(paymentMethod);
    }
  }

  @Override
  public PaymentMethod findByType(String type) {
    Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findByType(type);
    return paymentMethod.orElseThrow(() -> new ResourceNotFoundException("Payment method not found in database!"));
  }

  @Override
  public PaymentMethod findById(Long id) {
    Optional<PaymentMethod> paymentMethod = paymentMethodRepository.findById(id);
    return paymentMethod.orElseThrow(() -> new ResourceNotFoundException("Payment method not found in database!"));
  }

  @Transactional(readOnly = true)
  @Override
  public List<PaymentMethod> findAll() {
    return paymentMethodRepository.findAll();
  }

  @Override
  public PaymentMethod update(PaymentMethod paymentMethod) {
    findById(paymentMethod.getId());
    return paymentMethodRepository.save(paymentMethod);
  }

  @Override
  public void delete(Long id) {
    PaymentMethod paymentMethod = findById(id);
    paymentMethodRepository.delete(paymentMethod);
  }
}
