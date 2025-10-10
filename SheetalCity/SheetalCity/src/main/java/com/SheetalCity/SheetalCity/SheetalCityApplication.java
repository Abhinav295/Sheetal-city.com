package com.SheetalCity.SheetalCity;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.SheetalCity.SheetalCity.security.JwtUtil;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaServer
@EnableScheduling
@EnableWebSecurity
public class SheetalCityApplication {	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context =   SpringApplication.run(SheetalCityApplication.class, args);
		SheetalCityApplication sheetalCityApplication = new SheetalCityApplication();
		sheetalCityApplication.initiateService();
		/*JwtUtil jwtUtil = (JwtUtil) context.getBean(JwtUtil.class);
		System.out.println(jwtUtil.generateToken("ABHINAV"));
		System.out.println(jwtUtil.validateToken("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJBQkhJTkFWIiwiaWF0IjoxNzYwMTI5NzU2LCJleHAiOjE3NjAxMjk4MTZ9.g3lW-rIXCvi0RpbSmxa9MvEWZiKzG2FALPZQFhimFMlAUR5CI32jwtftPjCT43No","ABHINAV"));
		*/
	}
	
	
	private void  initiateService() {
		System.out.println("--------------------------OOO----------------------");
	}

}