package com.main.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    JwtFilter filter;

    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http,
                                           CorsConfigurationSource corsConfigurationSource)
            throws Exception {

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource))  // 🔥 IMPORTANT
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth

                    // ✅ OPTIONS (Preflight)
                    .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

                    // ✅ Public endpoints
                    .requestMatchers("/auth/**").permitAll()

                    // 🔐 ROLE BASED
                    .requestMatchers("/buyer/**").hasAuthority("BUYER")
                    .requestMatchers("/seller/**").hasAuthority("SELLER")
                    .requestMatchers("/admin/**").hasAuthority("ADMIN")

                    .anyRequest().authenticated()
            )

            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }





}
