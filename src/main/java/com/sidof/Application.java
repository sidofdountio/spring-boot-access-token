package com.sidof;

import com.sidof.security.model.Role;
import com.sidof.security.request.AddRoleToUserRequest;
import com.sidof.security.request.RegisterRequester;
import com.sidof.security.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(AppUserService appUserService ){
		return args->{

			appUserService.saveRole(new Role(null,"ROLE_USER"));
			appUserService.saveRole(new Role(null,"ROLE_ADMIN"));
			appUserService.saveRole(new Role(null,"ROLE_MANAGER"));

			appUserService.register(new RegisterRequester("Mike","kross","mikekross@gmail.com","123"));
			appUserService.register(new RegisterRequester("Havey","spector","haveyspector@gmail.com","123"));
			appUserService.register(new RegisterRequester("Hiro","hiro","hiro@gmail.com","123"));

			appUserService.addRoleToUser(new AddRoleToUserRequest("Mike","ROLE_ADMIN"));
			appUserService.addRoleToUser(new AddRoleToUserRequest("Havey","ROLE_ADMIN"));
			appUserService.addRoleToUser(new AddRoleToUserRequest("Hiro","ROLE_MANAGER"));
		};
	}

}
