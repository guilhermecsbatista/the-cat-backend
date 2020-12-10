package com.dev.thecat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TheCatBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheCatBackendApplication.class, args);
	}

}
