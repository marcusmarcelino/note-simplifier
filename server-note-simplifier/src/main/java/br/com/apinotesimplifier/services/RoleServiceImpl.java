package br.com.apinotesimplifier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.interfaces.RoleService;
import br.com.apinotesimplifier.models.Role;
import br.com.apinotesimplifier.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class RoleServiceImpl implements RoleService{
  @Autowired
  private RoleRepository roleRepository;
  @Override
  public Role saveRole(Role role) {
    log.info("Saving new role to the database ", role.getName());
    return this.roleRepository.save(role);
  }
  @Override
  public List<Role> getRoles() {
    return this.roleRepository.findAll();
  }
}
