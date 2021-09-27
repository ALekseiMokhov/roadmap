package com.gmail.qwertygoog.roadmap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Collections;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(proxyTargetClass = true)
public class ReactiveSecurityConfig {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";
    private static final String ADMIN_NAME = "32263750";
    private static final String ADMIN_DOUBLE_NAME = "112057915452899807987";
    private static final String ERROR = "/error";
    private static final String LOGOUT = "/logout";
    private static final String SIGN_IN = "/signin";
    private static final String SIGN_UP = "/signup";
    private static final String SEND = "/send";
    private static final String CSS = "/css/**";
    private static final String PNG = "/png/**";

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(
            ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .formLogin()
                .loginPage(SIGN_IN)
                .and()
                .authorizeExchange()
                .pathMatchers("/", SIGN_UP, SEND, CSS, PNG, SIGN_IN, ERROR, LOGOUT)
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login()
                .and()
                .exceptionHandling()
                .and()
                .logout()
                .logoutUrl(LOGOUT)
                .requiresLogout(ServerWebExchangeMatchers.pathMatchers(HttpMethod.GET, LOGOUT))
                .and()
                .addFilterAfter(authenticationWebFilter(), SecurityWebFiltersOrder.REACTOR_CONTEXT)
                .build();
    }

    public AuthenticationWebFilter authenticationWebFilter() {
        return new AuthenticationWebFilter(resolver());
    }


    public ReactiveAuthenticationManagerResolver<ServerWebExchange> resolver() {
        return exchange -> {
            exchange.getPrincipal()
                    .map(Principal::getName)
                    .flatMap(n -> {
                        if (n.equals(ADMIN_NAME) || n.equals(ADMIN_DOUBLE_NAME)) {
                            return Mono.just(adminAuthManager());
                        }
                        return Mono.just(visitorAuthManager());
                    });
            return Mono.just(visitorAuthManager());
        };
    }

    public ReactiveAuthenticationManager adminAuthManager() {
        return authentication ->
                admin(authentication)
                        .map(s -> new UsernamePasswordAuthenticationToken(
                                        authentication.getPrincipal(),
                                        authentication.getCredentials(),
                                        Collections.singletonList(new SimpleGrantedAuthority(ROLE_ADMIN))
                                )
                        );
    }

    public Mono<String> admin(Authentication authentication) {
        return Mono.just(authentication.toString());
    }

    public ReactiveAuthenticationManager visitorAuthManager() {
        return authentication ->
                visitor(authentication)
                        .map(s -> new UsernamePasswordAuthenticationToken(
                                        authentication.getPrincipal(),
                                        authentication.getCredentials(),
                                        Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER))
                                )
                        );
    }

    public Mono<String> visitor(Authentication authentication) {
        return Mono.just(authentication.toString());
    }


    /**
     * no encoder due to jasypt encryption
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
