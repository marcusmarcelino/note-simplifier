package br.com.apinotesimplifier.interfaces;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.apinotesimplifier.dto.ServiceProvidedDTO;
import br.com.apinotesimplifier.dto.ServiceProvidedFormDTO;
import br.com.apinotesimplifier.models.ServiceProvided;

public interface ServiceProvidedService {
  ServiceProvidedDTO save(ServiceProvided serviceProvided);

  ServiceProvidedDTO update(ServiceProvidedFormDTO serviceProvided);

  ServiceProvidedDTO endService(Long id);

  ServiceProvided findById(Long id);

  ServiceProvidedDTO findServiceProvidedDTOById(Long id);

  Page<ServiceProvidedDTO> findByIdClient(Long id, Pageable pageable);

  Page<ServiceProvidedDTO> findByIdProfessional(Long id, Pageable pageable);

  Page<ServiceProvidedDTO> findByServiceDate(LocalDate serviceDate, Pageable pageable);

  Page<ServiceProvidedDTO> findAll(Pageable pageable);

  void delete(Long id);
}
