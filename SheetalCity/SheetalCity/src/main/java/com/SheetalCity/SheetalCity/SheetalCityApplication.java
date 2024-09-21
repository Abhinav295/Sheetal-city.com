package com.SheetalCity.SheetalCity;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaServer
public class SheetalCityApplication {	
	
	public static void main(String[] args) {
		SpringApplication.run(SheetalCityApplication.class, args);
		SheetalCityApplication sheetalCityApplication = new SheetalCityApplication();
		sheetalCityApplication.initiateService();
	}
	
	
	private void  initiateService() {
		System.out.println("--------------------------OOO----------------------");
	}

}