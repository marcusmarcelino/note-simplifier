package br.com.apinotesimplifier.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.User;
import lombok.Data;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/all")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(this.userService.getUsers());
  }

  @PostMapping("/save")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
    return ResponseEntity.created(uri).body(this.userService.saveUser(user));
  }
  
  @PostMapping("/addtouser")
  public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
    this.userService.addRoleToUser(form.getUsername(), form.getRolename());
    return ResponseEntity.ok().build();
  }
}

@Data
  class RoleToUserForm {
    private String username;
    private String rolename;
  }
