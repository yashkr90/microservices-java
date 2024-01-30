package com.example.ibm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.example.ibm.model.Account;
import com.example.ibm.repo.AccountRepo;

import jakarta.annotation.PostConstruct;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient

public class AccountAppApplication 
//	implements CommandLineRunner
	{

	
	public AccountAppApplication(AccountRepo accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	private final AccountRepo accountRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AccountAppApplication.class, args);
	}
	

	@PostConstruct
	public void init() throws Exception {
		// TODO Auto-generated method stub

		List<Account> result = Stream
				.of(new Account(UUID.randomUUID().toString(), "Sachin", "Mumbai", "sachin@email.com",22.30),
						new Account(UUID.randomUUID().toString(), "Rahul", "Banglore", "rahul@email.com",23.67),
						new Account(UUID.randomUUID().toString(), "Sourav", "Kolkata", "sourav@email.com",45.78))
				.collect(Collectors.toList());
//		for (Account a : result) {
//			accountRepository.createAccount(a);
//		}

	}

	
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		accountRepository.createAccount(new Account(UUID.randomUUID().toString(), "Sachin", "Mumbai", "sachin@email.com"));
//		accountRepository.createAccount(new Account(UUID.randomUUID().toString(), "Rahul", "Banglore", "rahul@email.com"));
//		
//	}
	

	

}
