package br.com.apinotesimplifier;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import br.com.apinotesimplifier.interfaces.RoleService;
import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.PersonalData;
import br.com.apinotesimplifier.models.Role;
import br.com.apinotesimplifier.models.User;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class NoteSimplifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteSimplifierApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService) {
		return args -> {
			roleService.saveRole(new Role(null,"ROLE_USER"));
			roleService.saveRole(new Role(null, "ROLE_MANAGER"));
			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
			roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "john", "password", new ArrayList<>(), "active", new PersonalData(null, "John Jones", "63985888588", "Centro", "JK", "401", "Ao lado do palacio", "Palmas", "TO", null)));
			userService.saveUser(new User(null, "mary", "password", new ArrayList<>(), "active", new PersonalData(null, "Mary Jones", "63985888588", "Centro", "JK", "401", "Ao lado do palacio", "Palmas", "TO", null)));
			userService.saveUser(new User(null, "alex", "password", new ArrayList<>(), "active", new PersonalData(null, "Alex Jones", "63985888588", "Centro", "JK", "401", "Ao lado do palacio", "Maceió", "AL", null)));
			userService.saveUser(new User(null, "wilson", "password", new ArrayList<>(), "active", new PersonalData(null, "Wilson Jones", "63985888588", "Centro", "JK", "401", "Ao lado do palacio", "Maceió", "AL", null)));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("mary", "ROLE_MANAGER");
			userService.addRoleToUser("alex", "ROLE_ADMIN");
			userService.addRoleToUser("wilson", "ROLE_SUPER_ADMIN");
		};
	}
}
