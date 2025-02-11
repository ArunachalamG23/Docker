package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for frontend requests
            .cors() // Enable CORS (Configured separately)
            .and()
            .authorizeHttpRequests()
                .requestMatchers("/login", "/register").permitAll() // Allow public access to login
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginProcessingUrl("/login") // API login endpoint
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json");
                    response.getWriter().write("{\"message\": \"Login successful\"}");
                })
                .failureHandler((request, response, exception) -> {
                    response.setStatus(401);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\": \"Invalid credentials\"}");
                })
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json");
                    response.getWriter().write("{\"message\": \"Logout successful\"}");
                })
                .permitAll();

        return http.build();
    }
}

