package br.com.apinotesimplifier.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apinotesimplifier.models.ServiceProvided;
import br.com.apinotesimplifier.models.User;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Long> {
  Optional<List<ServiceProvided>> findByIdClient(User idClient);

  Optional<List<ServiceProvided>> findByIdProfessional(User idProfessional);

  Optional<List<ServiceProvided>> findByServiceDate(LocalDate serviceDate);
}
