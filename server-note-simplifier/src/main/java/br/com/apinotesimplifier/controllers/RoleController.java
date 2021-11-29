package br.com.apinotesimplifier.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apinotesimplifier.interfaces.RoleService;
import br.com.apinotesimplifier.models.Role;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
  @Autowired
  private RoleService roleService;

  @GetMapping("/all")
  public ResponseEntity<List<Role>> getRoles() {
    return ResponseEntity.ok().body(this.roleService.getRoles());
  }

  @PostMapping("/save")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
    return ResponseEntity.created(uri).body(this.roleService.saveRole(role));
  }
}
