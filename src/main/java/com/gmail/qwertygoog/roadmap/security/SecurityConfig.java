package com.gmail.qwertygoog.roadmap.security;

import org.springframework.context.annotation.AdviceMode;
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
@EnableReactiveMethodSecurity(mode = AdviceMode.PROXY)
public class SecurityConfig {
    private static final String ADMIN = "ROLE_ADMIN";
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(
            ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .formLogin()
                .and()
                .authorizeExchange()
                .pathMatchers("/", "/signup", "/send", "/css/**","/png/**").permitAll()
/*
                .pathMatchers("/group","/skill").hasAnyRole(ADMIN)
*/
                .anyExchange().authenticated()

                .and().build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }
}
