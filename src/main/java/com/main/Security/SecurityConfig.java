package com.main.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
            // ✅ CORS Enable
            .cors(cors -> cors.configurationSource(corsConfigurationSource))

            // ✅ Disable CSRF (JWT project me required)
            .csrf(csrf -> csrf.disable())

            // ✅ Stateless Session (JWT use kar rahe ho)
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // ✅ Authorization Rules
            .authorizeHttpRequests(auth -> auth

                    // 🔥 Preflight requests allow karo
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    // 🔓 Public endpoints
                    .requestMatchers("/auth/**").permitAll()

                    // 🔐 Role based access
                    .requestMatchers("/buyer/**").hasRole("BUYER")
                    .requestMatchers("/seller/**").hasRole("SELLER")
                    .requestMatchers("/admin/**").hasRole("ADMIN")

                    // 🔒 Baaki sab authenticated
                    .anyRequest().authenticated()
            )

            // ✅ JWT Filter Add karo
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
