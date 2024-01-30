package com.ibm.loan_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class LoanAppApplication {
	
	private final Environment environment;
	
	public LoanAppApplication(Environment environment) {
		super();
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(LoanAppApplication.class, args);
	}

	@GetMapping("/status")
	public ResponseEntity<?> checkStatus()
	{
		return ResponseEntity.ok("app runing on port: "+environment.getProperty("local.server.port"));
	}
}
