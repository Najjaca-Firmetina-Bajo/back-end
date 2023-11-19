package com.nfb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.nfb.modules")
@EntityScan("com.nfb.modules")
@ComponentScan("com.nfb.modules")
@ComponentScan(basePackages = "com.nfb.config")
public class MedicalSupplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalSupplyApplication.class, args);
	}

}
