package com.gmail.qwertygoog.roadmap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class ReactiveSecurityConfig {

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(
            ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .formLogin()
                .and()
                .authorizeExchange()
                .pathMatchers("/group")
                .hasAnyRole("ADMIN")
                .pathMatchers("/", "/signup", "/send", "/css/**", "/png/**")
                .permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2Login()
                .and()
                .exceptionHandling()
                .and()
                .build();
    }

    /**
     * no encoder due to jasypt encryption
     * todo пробросить вместе с jasypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
