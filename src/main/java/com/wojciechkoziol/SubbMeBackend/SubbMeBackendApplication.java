package com.wojciechkoziol.SubbMeBackend;

import java.util.HashSet;
import java.util.Set;

import com.wojciechkoziol.SubbMeBackend.auth.role.Role;
import com.wojciechkoziol.SubbMeBackend.auth.role.RoleRepository;
import com.wojciechkoziol.SubbMeBackend.auth.user.AppUser;
import com.wojciechkoziol.SubbMeBackend.auth.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication()
public class SubbMeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubbMeBackendApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(RoleRepository roleRepository) {
		return args ->{
			if (roleRepository.findByAuthority("USER").isPresent()) return;
			roleRepository.save(new Role("USER"));
		};
	}
}
