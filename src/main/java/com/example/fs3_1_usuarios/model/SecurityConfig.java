package com.example.fs3_1_usuarios.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF protection
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/users/admin/**").hasRole("ADMIN")  // Solo el admin puede acceder a estos endpoints
                .requestMatchers("/users/**").hasAnyRole("ADMIN", "USER")  // Ambos roles pueden acceder a estos endpoints
                .anyRequest().authenticated()
            )
            .httpBasic(); // Abilita autenticación básica 

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //Define en memoria los usuarios con sus roles (solo para testear)
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();
                
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

