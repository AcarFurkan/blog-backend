package com.acar.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class InsecurityConfiguration {
    // @formatter:off
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return  http
                .authorizeExchange(
                        exchanges -> exchanges
                                .pathMatchers("/users/**").permitAll()
                                .pathMatchers("/posts/**").permitAll()
                                .pathMatchers("/likes/**").permitAll()
                                .pathMatchers("/comments/**").permitAll()
                                .pathMatchers("/tags/**").permitAll()
                                .pathMatchers("/categories/**").permitAll()
                                .pathMatchers("/").permitAll()
                                .pathMatchers("/login").permitAll()
                                .pathMatchers("/register").permitAll()
                                .pathMatchers("/logout").permitAll()
                                .pathMatchers("/h2-console/**").permitAll()
                                .pathMatchers("/actuator/**").permitAll()
                                .anyExchange().authenticated()
                ).build();
    }
}