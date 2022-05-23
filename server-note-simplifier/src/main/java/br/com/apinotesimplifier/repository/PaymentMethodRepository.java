package br.com.apinotesimplifier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apinotesimplifier.models.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
  Optional<PaymentMethod> findByType(String type);
}
