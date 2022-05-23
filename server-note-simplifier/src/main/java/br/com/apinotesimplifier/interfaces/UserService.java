package br.com.apinotesimplifier.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.apinotesimplifier.dto.UserDTO;
import br.com.apinotesimplifier.dto.UserDataDTO;
import br.com.apinotesimplifier.models.User;

public interface UserService {
  User save(UserAndPersonalData userAndPersonalData);

  void addRoleToUser(String username, String rolename);

  User findByUsername(String username);

  User findById(Long id);

  UserDataDTO findUserDTOById(Long id);

  User update(User user);

  void delete(Long id);

  Page<UserDTO> findAll(Pageable pageable);

  List<UserDTO> findByRole(String role);
}