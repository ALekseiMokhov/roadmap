package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.User;
import com.gmail.qwertygoog.roadmap.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@WebFluxTest(controllers = IndexContoller.class)
class IndexContollerTest {
    private static final String PATH = "/";
    private static final String ADD_USER = "/signup";
    @MockBean
    private UserServiceImpl userService;
    @Autowired
    private WebTestClient webClient;

    @Test
    void saveUser() throws Exception {
        Mockito.when(userService.save(any())).thenReturn(Mono.just(new User()));
        webClient.post()
                .uri(ADD_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new User()))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }


}