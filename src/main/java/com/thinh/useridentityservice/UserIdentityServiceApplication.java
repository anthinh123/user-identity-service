package com.thinh.useridentityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserIdentityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserIdentityServiceApplication.class, args);
	}

}
