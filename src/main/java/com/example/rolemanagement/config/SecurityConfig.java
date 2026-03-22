package com.example.rolemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(JwtFilter jwtFilter, CustomAccessDeniedHandler accessDeniedHandler) {
        this.jwtFilter = jwtFilter;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Public endpoints
                        .requestMatchers("/auth/**").permitAll()

                        // Admin only
                        .requestMatchers("/roles/**").hasAuthority("ADMIN")
                        .requestMatchers("/permissions/**").hasAuthority("ADMIN")

                        // User endpoints
                        .requestMatchers(HttpMethod.GET, "/users/**")
                        .hasAnyAuthority("ADMIN", "MANAGER", "USER")
                        .requestMatchers(HttpMethod.POST, "/users/**")
                        .hasAnyAuthority("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/users/**")
                        .hasAnyAuthority("ADMIN", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/users/**")
                        .hasAuthority("ADMIN")

                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}