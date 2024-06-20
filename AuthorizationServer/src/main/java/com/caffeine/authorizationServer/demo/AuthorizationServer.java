package com.caffeine.authorizationServer.demo;

import com.caffeine.authorizationServer.demo.Model.User;
import com.caffeine.authorizationServer.demo.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan("com.caffeine.authorizationServer")
@SpringBootApplication
public class AuthorizationServer {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServer.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(
			UserRepository repo, PasswordEncoder passwordEncoder) {
		return args -> {
			repo.save(
					new User("caffeineadmin", passwordEncoder.encode("password"), "ROLE_ADMIN"));
		};
	}
}

