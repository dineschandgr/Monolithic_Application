package com.example.monolithic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude ={SecurityAutoConfiguration.class})
public class MonolithicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolithicApplication.class, args);
	}

}
