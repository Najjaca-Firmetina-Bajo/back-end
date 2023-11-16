package com.nfb.medicalsupply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.nfb.modules.stakeholders.core.domain.user")
public class MedicalSupplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalSupplyApplication.class, args);
	}

}
