package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.Level;
import com.gmail.qwertygoog.roadmap.model.Priority;
import com.gmail.qwertygoog.roadmap.model.Skill;
import com.gmail.qwertygoog.roadmap.service.SkillService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@WebFluxTest(controllers = SkillController.class)
class SkillControllerTest {
    private static final String GET_ALL = "/skill";
    private static final String ADD = "/skill/add";
    private static final String GET_LVL = "/skill/lvl";
    private static final String GET_PRIOR = "/skill/priority";
    private static final String GET = "/skill/get";
    private static final String DELETE = "/skill/delete";
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    SkillService service;

    private Skill skill;

    @BeforeEach
    void init() {
        skill = new Skill();
        skill.setLevel(Level.A);
        skill.setName("Important");
        skill.setPriority(Priority.HIGH);
    }


    @Test
    @WithMockUser
    void addSkill() {
        Mockito.when(service.add(any())).thenReturn(Mono.just(skill));
        webTestClient
                .mutateWith(csrf())
                .post()
                .uri(ADD)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(skill))
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
        ;

    }

    @Test
    void getAll() {
    }

    @Test
    void getAllByLvl() {
    }

    @Test
    void getSkill() {
    }

    @Test
    void deleteSkill() {
    }
}