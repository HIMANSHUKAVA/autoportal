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

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    JwtFilter filter;

    @Bean
    public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth

                    // ✅ PUBLIC PAYMENT LINK
                    .requestMatchers("/auth/fetch/single/payment/data/**").permitAll()

                    // ✅ OPTIONS (CORS)
                    .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

                    // 🔐 ROLE BASED
                    .requestMatchers("/buyer/**").hasAuthority("BUYER")
                    .requestMatchers("/seller/**").hasAuthority("SELLER")
                    .requestMatchers("/admin/**").hasAuthority("ADMIN")

                    // बाकी सब
                    .anyRequest().permitAll()
            )

            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }




}
