package com.gmail.qwertygoog.roadmap.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class SecurityTest {
    @Autowired
    ApplicationContext context;

    private WebTestClient client;

    @BeforeEach
    public void setup() {
        this.client = WebTestClient
                .bindToApplicationContext(this.context)
                .configureClient()
                .build();
    }

    @Test
    @WithUserDetails(value = "alex")
/*
    @WithMockUser(username="admin", authorities = { "ADMIN", "USER" })
*/
    public void whenHasCredentials_thenSeeIndexHTML() {
        this.client.get().uri("/group").exchange().expectStatus().isOk().expectBody(String.class);
    }

}
