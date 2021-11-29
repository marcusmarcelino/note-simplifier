package br.com.apinotesimplifier.interfaces;

import java.util.List;

import br.com.apinotesimplifier.models.User;

public interface UserService {
  User saveUser(UserAndPersonalData userAndPersonalData);

  void addRoleToUser(String username, String rolename);

  User getUser(String username);

  List<User> getUsers();
}