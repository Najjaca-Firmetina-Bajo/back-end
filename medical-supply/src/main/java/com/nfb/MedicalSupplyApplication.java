package com.nfb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MedicalSupplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalSupplyApplication.class, args);
	}

}
