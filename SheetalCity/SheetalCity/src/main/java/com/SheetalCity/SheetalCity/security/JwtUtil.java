package com.SheetalCity.SheetalCity.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String SECRET_KEY;

	public static final long JWT_TOKEN_VALIDITY = 60;

	public String generateToken(String username) {
		return Jwts.builder().subject(username).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(getSigningKey())
				.compact();
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean validateToken(String token, String userName) {
		final String username = extractUsername(token);
		System.out.println(isTokenExpired(token));
		return (username.equals(userName) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(getSigningKey()).build()
				.parseClaimsJws(token).getBody();
	}

	public String validateTokenAndGetUsername(String token) {
		 String username = extractUsername(token);
		 if(validateToken(token,username))
			 return username;
		return null;
	}

}
