package br.com.apinotesimplifier.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apinotesimplifier.error.ResourceNotFoundException;
import br.com.apinotesimplifier.interfaces.RoleService;
import br.com.apinotesimplifier.models.Role;
import br.com.apinotesimplifier.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role saveRole(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public List<Role> getRoles() {
    return roleRepository.findAll();
  }

  @Override
  public Role findRoleByName(String name) {
    Optional<Role> role = roleRepository.findByName(name);
    return role.orElseThrow(() -> new ResourceNotFoundException("The type '" + name + "' not exist in the database!"));
  }
}
