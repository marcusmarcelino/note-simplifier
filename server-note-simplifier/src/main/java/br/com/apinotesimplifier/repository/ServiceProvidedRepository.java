package br.com.apinotesimplifier.repository;

import java.time.LocalDate;
// import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import br.com.apinotesimplifier.models.ServiceProvided;
import br.com.apinotesimplifier.models.User;

public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Long> {
  // @Query("SELECT serviceProvided FROM ServiceProvided serviceProvided WHERE
  // serviceProvided.idClient.id = :idClient ")
  Page<ServiceProvided> findByIdClient(User idClient, Pageable pageable);

  Page<ServiceProvided> findByIdProfessional(User idProfessional, Pageable pageable);

  Page<ServiceProvided> findByServiceDate(LocalDate serviceDate, Pageable pageable);
}
