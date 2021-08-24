package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.MessageEvent;
import com.gmail.qwertygoog.roadmap.service.MailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@WebFluxTest(controllers = MailController.class)
class MailControllerTest {
    private static final String MAIL = "/mail";
    private static final String SEND = "/mail/send";

    @Autowired
    private WebTestClient webClient;
    @MockBean
    private MailSenderService mailSenderService;

    private MessageEvent event;

    @Test
    @WithMockUser
    void render() {
       webClient
                .mutateWith(csrf())
                .get()
                .uri(MAIL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    @WithMockUser
    void sendMessage() {
        event = new MessageEvent();
        event.setFrom("long_email@com");
        event.setText("extremely long important text");
        webClient
                .mutateWith(csrf())
                .post()
                .uri(SEND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(event))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
    @Test
    @WithAnonymousUser
    void sendMessageWithWrongCredentials() {
        event = new MessageEvent();
        webClient
                .mutateWith(csrf())
                .post()
                .uri(SEND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(event))
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}