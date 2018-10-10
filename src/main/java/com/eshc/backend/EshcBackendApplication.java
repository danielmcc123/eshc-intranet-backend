package com.eshc.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




//@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
@SpringBootApplication
public class EshcBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EshcBackendApplication.class, args);
	}
}