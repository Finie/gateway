package com.finstudio.gateway;

import com.finstudio.gateway.model.UserProfile;
import com.finstudio.gateway.services.ApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GatewayApplication.class, args);


		ApiService apiService = context.getBean(ApiService.class);
		List<UserProfile> response = apiService.getUserList();


		System.out.println("API Response: " + response);
	}




}
