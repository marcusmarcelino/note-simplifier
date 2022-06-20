package br.com.apinotesimplifier.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.dto.UserDTO;
import br.com.apinotesimplifier.dto.UserDataDTO;
import br.com.apinotesimplifier.enums.AccountStatus;
import br.com.apinotesimplifier.error.ResourceNotFoundException;
import br.com.apinotesimplifier.interfaces.RoleService;
import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.Role;
import br.com.apinotesimplifier.models.User;
import br.com.apinotesimplifier.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepository userRepository;
  private final RoleService roleService;
  @Autowired
  private PasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findByUsername(username);
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    });
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
  }

  @Override
  public UserDataDTO save(User user) {
    user.getIdPersonalData().setIdUser(user);
    user.setPassword(encoder.encode(user.getPassword()));
    return new UserDataDTO(userRepository.save(user));
  }

  @Override
  public void addRoleToUser(String username, String rolename) {
    User user = findByUsername(username);
    Role role = roleService.findRoleByName(rolename);
    user.getRoles().add(role);
  }

  @Transactional(readOnly = true)
  @Override
  public User findByUsername(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    return user.orElseThrow(() -> new ResourceNotFoundException("User not found in the database!"));
  }

  @Transactional(readOnly = true)
  @Override
  public User findById(Long id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> new ResourceNotFoundException("User id = " + id + ", not found in the database!"));
  }

  @Transactional(readOnly = true)
  @Override
  public UserDataDTO findUserDTOById(Long id) {
    Optional<User> user = userRepository.findById(id);
    return new UserDataDTO(user.orElseThrow(() -> new ResourceNotFoundException("User not found in the database!")));
  }

  @Override
  public User update(User user) {
    findById(user.getId());
    return userRepository.save(user);
  }

  @Override
  public void delete(Long id) {
    User user = findById(id);
    userRepository.delete(user);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<UserDTO> findAll(Pageable pageable) {
    Page<User> result = userRepository.findAll(pageable);
    return result.map(user -> new UserDTO(user));
  }

  @Transactional(readOnly = true)
  @Override
  public List<UserDTO> findByRole(String role) {
    Optional<List<UserDTO>> users = userRepository.findByRole(role);
    return users.orElseThrow(() -> new ResourceNotFoundException("Users not found in the database!"));
  }

  @Override
  public void updateAccountStatus(String status, Long id) {
    User user = findById(id);
    user.setAccountStatus(AccountStatus.valueOf(status));
  }

  @Override
  public void disableAccount(Long id) {
    User user = findById(id);
    user.setAccountStatus(AccountStatus.blocked);
  }
}
