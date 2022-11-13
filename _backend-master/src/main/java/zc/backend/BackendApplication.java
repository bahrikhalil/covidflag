package zc.backend;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import zc.backend.repository.RoleRepo;
import zc.backend.repository.UserRepo;


@SpringBootApplication
@CrossOrigin("*")
public class BackendApplication {



	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

	}
	@Bean
	PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	@Bean
	CommandLineRunner run(UserRepo userRepo, RoleRepo roleRepo) {
		return args -> {
		//	Role role = new Role(1,"PUBLISHER");
			//roleRepo.save(role);
		//	userRepo.save(new Users("bahri1","bahri1@gmail.com","solomid123",role));
		};
	}


	}







