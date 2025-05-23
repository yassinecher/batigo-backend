package com.batigobackend.batigo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BatigoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatigoApplication.class, args);
	}

}
