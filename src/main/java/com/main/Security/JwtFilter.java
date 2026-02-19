package com.main.Security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil util;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

    	String path = request.getServletPath();

    	//  Bypass public routes
    	if (path.startsWith("/auth") || path.startsWith("/images")) {
    	    filterChain.doFilter(request, response);
    	    return;
    	}


        

        String header = request.getHeader("Authorization");

        System.out.println("JWT FILTER HIT");
        System.out.println("Authorization Header: " + header);
        
        

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            
            
            try {
                Claims claims = util.validateToken(token);

                String username = claims.getSubject();
                String role = claims.get("role", String.class);
                System.out.println("ROLE FROM TOKEN: " + role);
                
                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority("ROLE_" + role);


                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(authority)
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }



}
