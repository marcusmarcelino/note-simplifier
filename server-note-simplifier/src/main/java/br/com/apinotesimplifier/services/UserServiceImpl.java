package br.com.apinotesimplifier.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.Role;
import br.com.apinotesimplifier.models.User;
import br.com.apinotesimplifier.repository.RoleRepository;
import br.com.apinotesimplifier.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Override
  public User saveUser(User user) {
    log.info("Saving new user {} to the database", user.getIdPersonalData().getName());
    return this.userRepository.save(user);
  }

  @Override
  public void addRoleToUser(String username, String rolename) {
    log.info("Adding role {} ro user {}", rolename, username);
    User user = this.userRepository.findByUsername(username);
    Role role = this.roleRepository.findByName(rolename);
    user.getRoles().add(role);
  }

  @Override
  public User getUser(String username) {
    log.info("Fetching user {} ", username);
    return this.userRepository.findByUsername(username);
  }

  @Override
  public List<User> getUsers() {
    log.info("Fetching all users");
    return this.userRepository.findAll();
  }

}
