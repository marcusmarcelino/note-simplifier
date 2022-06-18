package br.com.apinotesimplifier.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apinotesimplifier.models.ServiceProvided;
import br.com.apinotesimplifier.models.User;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Long> {
  Page<ServiceProvided> findByIdClient(User idClient, Pageable pageable);

  Page<ServiceProvided> findByIdProfessional(User idProfessional, Pageable pageable);

  Page<ServiceProvided> findByServiceDate(LocalDate serviceDate, Pageable pageable);
}
