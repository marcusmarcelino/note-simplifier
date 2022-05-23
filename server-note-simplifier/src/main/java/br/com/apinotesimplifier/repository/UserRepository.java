package br.com.apinotesimplifier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.apinotesimplifier.dto.UserDTO;
import br.com.apinotesimplifier.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  @Query("SELECT new br.com.apinotesimplifier.dto.UserDTO(u) FROM User u INNER JOIN u.roles r WHERE r.name = :role")
  Optional<List<UserDTO>> findByRole(@Param("role") String role);
}
