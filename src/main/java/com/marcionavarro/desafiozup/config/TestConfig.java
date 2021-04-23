package com.marcionavarro.desafiozup.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcionavarro.desafiozup.entities.Address;
import com.marcionavarro.desafiozup.entities.User;
import com.marcionavarro.desafiozup.respositories.AddressRepository;
import com.marcionavarro.desafiozup.respositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRespository;

	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Maria Brown",  "988888888", "maria@gmail.com", "123456");
		User u2 = new User(null, "Alex Green", "977777777",  "alex@gmail.com", "123456");

		Address a1 = new Address(null, "Rua Caetite", 712, "casa", "Cidade Nova", "Manaus", "AM", "69096550", u1);
		Address a2 = new Address(null, "Rua Manoel Messias dos Santos", 810, "Ap", "Vila Manoel Taveira",
				"Campo Grande", "MS", "79115671", u2);
		Address a3 = new Address(null, "Rua Brasília", 123, "Ap", "Incra", "Cacoal", "RO", "76965878", u1);

		userRepository.saveAll(Arrays.asList(u1, u2));
		
		addressRespository.saveAll(Arrays.asList(a1, a2, a3));
	}
}
