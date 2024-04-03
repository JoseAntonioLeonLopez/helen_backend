package com.helen.Service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.helen.Entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private String SECRET_KEY = "cbe9e16cf9ef4dfd75f9d94da7361bc49fb6981fdcc860fe7700864f519c2d1f";
	
	public String extractEmail(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public boolean isValid(String token, User user) {
		String email = extractEmail(token);
		return (email.equals(user.getEmail())) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSigninKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public String generateToken(User user) {
		String token = Jwts
				.builder()
				.subject(user.getEmail())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+ 24*60*60*1000))
				.signWith(getSigninKey())
				.compact();
		
		return token;
	}
	
	private SecretKey getSigninKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
