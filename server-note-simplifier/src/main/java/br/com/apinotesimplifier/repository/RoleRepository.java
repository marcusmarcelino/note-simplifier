package br.com.apinotesimplifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apinotesimplifier.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
