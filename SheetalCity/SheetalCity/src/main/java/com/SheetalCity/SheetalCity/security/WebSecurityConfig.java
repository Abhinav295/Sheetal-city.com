package com.SheetalCity.SheetalCity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class WebSecurityConfig {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.cors().and().csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth.requestMatchers("/sheetal.city/auth/**").permitAll()
				.anyRequest().authenticated()).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		

        return httpSecurity.build();
    }
	
}
