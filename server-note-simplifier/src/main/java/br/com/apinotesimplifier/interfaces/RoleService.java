package br.com.apinotesimplifier.interfaces;

import java.util.List;

import br.com.apinotesimplifier.models.Role;

public interface RoleService {
  Role saveRole(Role role);

  List<Role> getRoles();

  Role findRoleByName(String name);
}
