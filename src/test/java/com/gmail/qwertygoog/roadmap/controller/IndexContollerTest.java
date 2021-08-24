package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.User;
import com.gmail.qwertygoog.roadmap.model.UserRole;
import com.gmail.qwertygoog.roadmap.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@WebFluxTest(controllers = IndexContoller.class)
class IndexContollerTest {

    private static final String ADD_USER = "/signup";
    private static final String GET = "/";
    @Autowired
    private WebTestClient webClient;
    @MockBean
    private UserServiceImpl userService;
    private User user;

    @BeforeEach
    void init() {
        user = new User();
        user.setRole(UserRole.ROLE_USER);
        user.setUsername("Уася");
        user.setPassword("long_andStronk3");
    }


    @Test
    void testConfigIsOk() {
        System.out.println("Config is up!");
        assertEquals(2, (1 + 1));
    }
    @Test
    @WithMockUser
    void testGetAll(){
        webClient
                .mutateWith(csrf())
                .get()
                .uri(GET)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    @WithMockUser
    void saveUser() throws Exception {
        Mockito.when(userService.save(any())).thenReturn(Mono.just(user));
        webClient
                .mutateWith(csrf())
                .post()
                .uri(ADD_USER)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(user))
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }


}