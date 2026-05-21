package com.noriservices.noristock.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService  {

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Value("${api.security.token.expiration}")
    private long expiration;

    private static final String ISSUER = "nori-stock-api";

    public String generateToken(UserDetails user){
        return Jwts.builder()
                .issuer(ISSUER)
                .issuedAt(new Date())
                .subject(user.getUsername())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSingKey())
                .compact();
    }

    public boolean isValidToken(String token, UserDetails user){
        try {
            String userNameToken = extractClaim(token, Claims::getSubject);
            return userNameToken.equals(user.getUsername())
                    && user.isEnabled()
                    && !isTokenExpired(token);
        }catch (JwtException e){
            log.error("This is not a valid token: " + token + "\n"+ e + "\n");
            return false;
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .requireIssuer(ISSUER)
                .verifyWith(getSingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token){
       return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private SecretKey getSingKey() {
       return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
