package com.ddu.college_helpdesk_system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/departments").permitAll()
                        .requestMatchers("/api/departments/**").hasRole("ADMIN")
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/tickets/raise/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/api/tickets/student/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/api/tickets/department/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.POST, "/api/responses/**").hasRole("STAFF")
                        .requestMatchers(HttpMethod.PUT, "/api/tickets/*/status").hasRole("STAFF")
                        .requestMatchers(HttpMethod.GET, "/api/tickets/**").hasAnyRole("ADMIN", "STAFF")
                        .requestMatchers(HttpMethod.DELETE, "/api/tickets/**").hasAnyRole("ADMIN", "STUDENT")
                        .requestMatchers(HttpMethod.PUT, "/api/tickets/*").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/api/tickets/unresolved").hasAnyRole("ADMIN", "STAFF")
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.init(http));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
