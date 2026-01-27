package com.example.iot_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // React static
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/assets/**",
                                "/*.js",
                                "/*.css",
                                "/*.ico")
                        .permitAll()

                        // API IoT
                        .requestMatchers("/api/**").permitAll()

                        // còn lại cho phép hết (DEV MODE)
                        .anyRequest().permitAll());

        return http.build();
    }
}
