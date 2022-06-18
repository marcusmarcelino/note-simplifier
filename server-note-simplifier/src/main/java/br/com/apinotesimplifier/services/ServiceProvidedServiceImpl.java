package br.com.apinotesimplifier.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.dto.ServiceProvidedDTO;
import br.com.apinotesimplifier.dto.ServiceProvidedFormDTO;
import br.com.apinotesimplifier.enums.ProgressStatus;
import br.com.apinotesimplifier.error.ResourceNotFoundException;
import br.com.apinotesimplifier.interfaces.PaymentMethodService;
import br.com.apinotesimplifier.interfaces.ServiceProvidedService;
import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.PaymentMethod;
import br.com.apinotesimplifier.models.ServiceProvided;
import br.com.apinotesimplifier.models.User;
import br.com.apinotesimplifier.repository.ServiceProvidedRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ServiceProvidedServiceImpl implements ServiceProvidedService {

  @Autowired
  private ServiceProvidedRepository serviceProvidedRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private PaymentMethodService paymentMethodService;

  @Override
  public ServiceProvidedDTO save(ServiceProvided serviceProvided) {
    User client = userService.findById(serviceProvided.getIdClient().getId());
    User professional = userService.findById(serviceProvided.getIdProfessional().getId());

    List<PaymentMethod> payMethods = new ArrayList<>();
    serviceProvided.getIdPaymentForServiceProvided().getPaymentMethods().forEach((payMeth) -> {
      PaymentMethod paymentMethod = paymentMethodService.findById(payMeth.getId());
      payMethods.add(paymentMethod);
    });
    serviceProvided.setIdClient(client);
    serviceProvided.setIdProfessional(professional);
    serviceProvided.getIdPaymentForServiceProvided().setPaymentMethods(payMethods);
    ServiceProvided serviceProvidedCreated = serviceProvidedRepository.save(serviceProvided);
    serviceProvidedCreated.getIdPaymentForServiceProvided().setIdServiceProvided(serviceProvidedCreated);

    return new ServiceProvidedDTO(serviceProvidedCreated);
  }

  @Transactional(rollbackFor = { Exception.class })
  @Override
  public ServiceProvidedDTO update(ServiceProvidedFormDTO serviceProvidedForm) throws Exception {
    ServiceProvided serviceProvided = findById(serviceProvidedForm.getId());

    if (serviceProvided.getSituation().equals(ProgressStatus.FINALIZED)) {
      String status = serviceProvided.getSituation().toString();
      throw new Exception("The sale cannot be changed as it is listed as: " + status);
    }

    serviceProvided.setServiceDescription(serviceProvidedForm.getServiceDescription());
    serviceProvided.setServiceDate(serviceProvidedForm.getServiceDate());
    serviceProvided.setVlTotal(serviceProvidedForm.getVlTotal());

    return new ServiceProvidedDTO(serviceProvidedRepository.save(serviceProvided));
  }

  @Override
  public ServiceProvidedDTO endService(Long id) {
    ServiceProvided serviceProvided = findById(id);
    serviceProvided.setSituation(ProgressStatus.FINALIZED);
    return new ServiceProvidedDTO(serviceProvided);
  }

  @Transactional(readOnly = true)
  @Override
  public ServiceProvided findById(Long id) {
    Optional<ServiceProvided> serviceProvided = serviceProvidedRepository.findById(id);
    return serviceProvided.orElseThrow(() -> new ResourceNotFoundException("Service provided not found in database!"));
  }

  @Transactional(readOnly = true)
  @Override
  public ServiceProvidedDTO findServiceProvidedDTOById(Long id) {
    ServiceProvided serviceProvided = findById(id);
    return new ServiceProvidedDTO(serviceProvided);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<ServiceProvidedDTO> findByIdClient(Long id, Pageable pageable) {
    User client = userService.findById(id);
    Page<ServiceProvided> page = serviceProvidedRepository.findByIdClient(client, pageable);
    return page.map(servProv -> new ServiceProvidedDTO(servProv));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<ServiceProvidedDTO> findByIdProfessional(Long id, Pageable pageable) {
    User professional = userService.findById(id);
    Page<ServiceProvided> page = serviceProvidedRepository.findByIdProfessional(professional, pageable);
    return page.map((serviceProvided) -> new ServiceProvidedDTO(serviceProvided));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<ServiceProvidedDTO> findAll(Pageable pageable) {
    Page<ServiceProvided> page = serviceProvidedRepository.findAll(pageable);
    return page.map((serviceProvided) -> new ServiceProvidedDTO(serviceProvided));
  }

  @Transactional(readOnly = true)
  @Override
  public Page<ServiceProvidedDTO> findByServiceDate(LocalDate serviceDate, Pageable pageable) {
    Page<ServiceProvided> page = serviceProvidedRepository.findByServiceDate(serviceDate, pageable);
    return page.map((serviceProvided) -> new ServiceProvidedDTO(serviceProvided));
  }

  @Override
  public void delete(Long id) {
    ServiceProvided servideProvided = findById(id);
    serviceProvidedRepository.delete(servideProvided);
  }
}
