package com.nfb.medicalsupply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EntityScan("com.nfb.modules.stakeholders.core.domain.user")
@EntityScan("com.nfb.modules.stakeholders.core.domain")
@ComponentScan("com.nfb.buildingblocks.core.usecases.impl")
@ComponentScan("com.nfb.modules.stakeholders")
//@ComponentScan("com.nfb.modules.stakeholders.API.controllers")

public class MedicalSupplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalSupplyApplication.class, args);
	}

}
