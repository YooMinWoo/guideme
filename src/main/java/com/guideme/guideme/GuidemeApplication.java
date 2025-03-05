package com.guideme.guideme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GuidemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuidemeApplication.class, args);
	}

}
