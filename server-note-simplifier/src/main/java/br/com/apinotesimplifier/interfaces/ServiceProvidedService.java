package br.com.apinotesimplifier.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.apinotesimplifier.models.ServiceProvided;
import br.com.apinotesimplifier.models.User;

public interface ServiceProvidedService {
  ServiceProvided saveServiceProvided(ServiceProvided serviceProvided);

  ServiceProvided saveServiceprovidedWithIds(ServiceProvided serviceProvided, Long idProfissional, Long idClient);

  ServiceProvided getServiceProvidedById(Long id);

  List<ServiceProvided> getServiceProvidedByIdClient(User idClient);

  List<ServiceProvided> getServiceProvidedByIdProfessional(User idProfessional);

  List<ServiceProvided> getServiceProvidedByServiceDate(LocalDate serviceDate);

  List<ServiceProvided> getServicesProvided();
}
