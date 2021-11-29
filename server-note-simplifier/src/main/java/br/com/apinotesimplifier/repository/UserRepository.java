package br.com.apinotesimplifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apinotesimplifier.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
  User findByUsername(String username);
}
