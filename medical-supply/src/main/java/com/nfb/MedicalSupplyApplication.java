package com.nfb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.nfb.modules.stakeholders.core")
@EntityScan("com.nfb.modules.stakeholders.core.domain")
@ComponentScan("com.nfb.modules.stakeholders")
@ComponentScan(basePackages = "com.nfb.config")
public class MedicalSupplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalSupplyApplication.class, args);
	}

}
