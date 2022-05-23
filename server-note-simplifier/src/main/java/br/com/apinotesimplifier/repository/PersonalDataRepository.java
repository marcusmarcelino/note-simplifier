package br.com.apinotesimplifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apinotesimplifier.models.PersonalData;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
}
