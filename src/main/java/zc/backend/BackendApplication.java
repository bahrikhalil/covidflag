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

import zc.backend.modles.Event;
import zc.backend.modles.Role;
import zc.backend.modles.Users;
import zc.backend.repository.EventRepo;
import zc.backend.repository.RoleRepo;
import zc.backend.repository.UserRepo;

import java.util.Arrays;
import java.util.List;


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
	/*@Bean
	CommandLineRunner run(UserRepo userRepo, RoleRepo roleRepo, EventRepo eventRepo) {
		return args -> {
			Event event1 = new Event(1,"event1","event1_desc");
			Event event2 = new Event(2,"event2","event2_desc");
			Event event3 = new Event(3,"event3","event3_desc");
			eventRepo.save(event1);
			eventRepo.save(event2);
			eventRepo.save(event3);
			List<Event> eventList = List.of(event1,event2);
			List<Event> eventList1 = List.of(event3);

			Role role = new Role(1,"PUBLISHER");
			roleRepo.save(role);
			userRepo.save(new Users(1,"bahri1","bahri1@gmail.com","solomid123",role,eventList));
			Role role1 = new Role(2,"role1");
			roleRepo.save(role1);
			userRepo.save(new Users(2,"khalil","khalil@gmail.com","solomid123",role1,eventList1));
		};
	}*/


	}







