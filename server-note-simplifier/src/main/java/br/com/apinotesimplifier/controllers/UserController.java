package br.com.apinotesimplifier.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apinotesimplifier.dto.UserDTO;
import br.com.apinotesimplifier.dto.UserDataDTO;
import br.com.apinotesimplifier.interfaces.RoleService;
import br.com.apinotesimplifier.interfaces.RoleToUserForm;
import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.Role;
import br.com.apinotesimplifier.models.User;

@RestController
@RequestMapping("/api/")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private RoleService roleService;

  private String AUTHORIZATION = "Authorization";
  private String APPLICATION_JSON_VALUE = "application/json";

  // @Value("${AUTHORIZATION}") private String AUTHORIZATION;
  // @Value("${APPLICATION_JSON_VALUE}") private String APPLICATION_JSON_VALUE;

  @GetMapping("users/all")
  public ResponseEntity<Page<UserDTO>> getUsers(Pageable pageable) {
    return ResponseEntity.ok().body(userService.findAll(pageable));
  }

  @PostMapping("users/save")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
    return ResponseEntity.created(uri).body(userService.save(user));
  }

  @PostMapping("users/addtouser")
  public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
    userService.addRoleToUser(form.getUsername(), form.getRolename());
    return ResponseEntity.ok().build();
  }

  @GetMapping("token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws StreamWriteException, DatabindException, IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        String refresh_token = authorizationHeader.substring("Bearer ".length());

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refresh_token);
        String username = decodedJWT.getSubject();
        User user = userService.findByUsername(username);

        String access_token = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 1200 * 1000))
            .withIssuer(request.getRequestURL().toString())
            .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
            .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
      } catch (Exception e) {
        response.setHeader("error", e.getMessage());
        response.setStatus(403);
        Map<String, String> error = new HashMap<>();
        error.put("error_message", e.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
      }
    } else {
      throw new RuntimeException("Refresh token is missing");
    }
  }

  @GetMapping("users/role")
  public ResponseEntity<List<UserDTO>> getUsersByRole(@RequestParam String role) {
    roleService.findRoleByName(role);
    return ResponseEntity.ok().body(userService.findByRole(role));
  }

  @GetMapping("users/{id}")
  public ResponseEntity<UserDataDTO> getUsersById(@PathVariable Long id) {
    return ResponseEntity.ok().body(userService.findUserDTOById(id));
  }
}