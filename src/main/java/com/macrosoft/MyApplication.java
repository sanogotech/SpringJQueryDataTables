package com.macrosoft;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.macrosoft.model.Role;
import com.macrosoft.model.User;
import com.macrosoft.repository.UserRepository;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {
	
	private  final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}
	
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		List<String> userdata = Arrays.asList("ADMIN", "ADMIN", "admin@test.com","admin2017");
        logger.debug("**** Create default user  {}", userdata);
		
		User user = new User();
		user.setName("ADMIN");
		user.setLastName("ADMIN");
		user.setEmail("admin@test.com");
		//BCryptPasswordEncoder bCryptPasswordEncoderLocal = new BCryptPasswordEncoder();
		//String encodepwd = bCryptPasswordEncoderLocal.encode("admin2017");
		//user.setPassword(encodepwd);
		//System.out.println("admin2017  encoder = " +encodepwd);
		user.setPassword("$2a$10$fE7BKQcc.tesDzaptjL8luXZB6MV5rvUJ13ub5aVYKqnoPmMqYd8m");
		user.setActive(true);
		//Role
		HashSet<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setRole("ROLE_ADMIN");
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
		
		//***
		
		User userClient = new User();
		userClient.setName("USER");
		userClient.setLastName("USER");
		userClient.setEmail("user@test.com");
		//BCryptPasswordEncoder bCryptPasswordEncoderLocal = new BCryptPasswordEncoder();
		//String encodepwd = bCryptPasswordEncoderLocal.encode("admin2017");
		//user.setPassword(encodepwd);
		//System.out.println("admin2017  encoder = " +encodepwd);
		userClient.setPassword("$2a$10$fE7BKQcc.tesDzaptjL8luXZB6MV5rvUJ13ub5aVYKqnoPmMqYd8m");
		userClient.setActive(true);
		//Role
		HashSet<Role> rolesClient = new HashSet<Role>();
		Role roleClient = new Role();
		roleClient.setRole("ROLE_USER");
		rolesClient.add(roleClient);
		userClient.setRoles(rolesClient);
		userRepository.save(userClient);
		
	}
	
			
}
