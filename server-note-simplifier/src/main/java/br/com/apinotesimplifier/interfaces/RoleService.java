package br.com.apinotesimplifier.interfaces;

import java.util.List;

import br.com.apinotesimplifier.models.Role;

public interface RoleService {
  Role save(Role role);

  Role update(Role role);

  List<Role> getRoles();

  Role findRoleByName(String name);

  Role findById(Long id);

  void delete(Long id);
}
