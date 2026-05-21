package com.noriservices.noristock.security.filter;

import com.noriservices.noristock.service.JwtService;
import com.noriservices.noristock.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }
        var username = jwtService.extractUsername(token);

        UserDetails user = userDetailsService.loadUserByUsername(username);
        boolean validToken = jwtService.isValidToken(token, user);

        if(!validToken){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        var authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if(auth == null) return null;

        return auth.replace("Bearer ", "");
    }
}
