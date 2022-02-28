package com.infedelis.socialNetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.infedelis.controller", "com.infedelis.service"})
@EnableJpaRepositories(basePackages = "com.infedelis.repository")
@EntityScan(basePackages = "com.infedelis.model")

public class SocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApplication.class, args);
	}
}
