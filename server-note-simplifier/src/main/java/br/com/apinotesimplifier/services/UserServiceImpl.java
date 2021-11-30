package br.com.apinotesimplifier.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.interfaces.UserAndPersonalData;
import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.PersonalData;
import br.com.apinotesimplifier.models.Role;
import br.com.apinotesimplifier.models.User;
import br.com.apinotesimplifier.repository.PersonalDataRepository;
import br.com.apinotesimplifier.repository.RoleRepository;
import br.com.apinotesimplifier.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder encoder;
  @Autowired
  private PersonalDataRepository personalDataRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByUsername(username);

    if(user == null) {
      log.error("User not found in the database");
      throw new UsernameNotFoundException("User not found in the database");
    } else {
      log.error("User found in the database: {}", username);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> { 
      authorities.add(new SimpleGrantedAuthority(role.getName())); 
    });
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
  }

  @Override
  public User saveUser(UserAndPersonalData userAndPersonalData) {
    PersonalData personalData = this.personalDataRepository.save(userAndPersonalData.getPersonalData());
    User userResp = userAndPersonalData.getUser();
    userResp.setPassword(this.encoder.encode(userResp.getPassword()));
    User user = this.userRepository.save(userResp);
    user.setIdPersonalData(personalData);
    log.info("Saving new user {} to the database", user.getIdPersonalData().getName());
    return user;
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
