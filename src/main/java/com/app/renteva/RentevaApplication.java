package com.app.renteva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.app.renteva")
public class RentevaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentevaApplication.class, args);
	}

}
