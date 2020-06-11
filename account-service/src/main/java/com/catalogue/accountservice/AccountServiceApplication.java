package com.catalogue.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AccountServiceApplication {
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		RestTemplate template = new RestTemplate();
		template.setErrorHandler(new ClientErrorHandler());
		return template;
	};
	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
