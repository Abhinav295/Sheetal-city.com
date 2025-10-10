package com.SheetalCity.SheetalCity.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String auth = request.getHeader("Authorization");
		if(auth!=null && auth.startsWith("Bearer")) {
			String token = auth.substring(7);
			String user = jwtUtil.validateTokenAndGetUsername(token);
			if(user!=null) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null, List.of(new SimpleGrantedAuthority("User")));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
