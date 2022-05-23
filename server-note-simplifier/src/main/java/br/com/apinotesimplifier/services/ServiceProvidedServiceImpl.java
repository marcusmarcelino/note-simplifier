package br.com.apinotesimplifier.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.error.ResourceNotFoundException;
import br.com.apinotesimplifier.interfaces.ServiceProvidedService;
import br.com.apinotesimplifier.models.ServiceProvided;
import br.com.apinotesimplifier.models.User;
import br.com.apinotesimplifier.repository.ServiceProvidedRepository;
import br.com.apinotesimplifier.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ServiceProvidedServiceImpl implements ServiceProvidedService {

  @Autowired
  private ServiceProvidedRepository serviceProvidedRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  public ServiceProvided saveServiceProvided(ServiceProvided serviceProv) {
    return serviceProvidedRepository.save(serviceProv);
  }

  @Override
  public ServiceProvided getServiceProvidedById(Long id) {
    Optional<ServiceProvided> serviceProvided = serviceProvidedRepository.findById(id);
    return serviceProvided.orElseThrow(() -> new ResourceNotFoundException("Service provided not found!"));
  }

  @Override
  public List<ServiceProvided> getServiceProvidedByIdClient(User idClient) {
    Optional<List<ServiceProvided>> serviceProvidedList = serviceProvidedRepository.findByIdClient(idClient);
    return serviceProvidedList.orElse(new ArrayList<>());
  }

  @Override
  public List<ServiceProvided> getServiceProvidedByIdProfessional(User idProfessional) {
    Optional<List<ServiceProvided>> serviceProvidedList = serviceProvidedRepository
        .findByIdProfessional(idProfessional);
    return serviceProvidedList.orElse(new ArrayList<>());
  }

  @Override
  public List<ServiceProvided> getServicesProvided() {
    return serviceProvidedRepository.findAll();
  }

  @Override
  public List<ServiceProvided> getServiceProvidedByServiceDate(LocalDate serviceDate) {
    Optional<List<ServiceProvided>> serviceProvidedList = serviceProvidedRepository.findByServiceDate(serviceDate);
    return serviceProvidedList.orElse(new ArrayList<>());
  }

  @Override
  public ServiceProvided saveServiceprovidedWithIds(ServiceProvided serviceProvided, Long idProfissional,
      Long idClient) {
    Optional<User> profissional = userRepository.findById(idProfissional);
    Optional<User> client = userRepository.findById(idClient);

    profissional.ifPresentOrElse((profi) -> serviceProvided.setIdProfessional(profi),
        () -> new ResourceNotFoundException("Profissional not found!"));
    client.ifPresentOrElse((cli) -> serviceProvided.setIdClient(cli),
        () -> new ResourceNotFoundException("Cliente not found!"));

    serviceProvided.setIdClient(client.get());
    serviceProvided.setIdProfessional(profissional.get());
    return serviceProvidedRepository.save(serviceProvided);
  }
}
