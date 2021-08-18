package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.RoadmapApplication;
import com.gmail.qwertygoog.roadmap.model.User;
import com.gmail.qwertygoog.roadmap.model.UserRole;
import com.gmail.qwertygoog.roadmap.repository.SkillGroupRepository;
import com.gmail.qwertygoog.roadmap.repository.SkillRepository;
import com.gmail.qwertygoog.roadmap.repository.UserRepo;
import com.gmail.qwertygoog.roadmap.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
/*
@ExtendWith(SpringExtension.class)
*/

@WebFluxTest(controllers = IndexContoller.class)
class IndexContollerTest {
    private static final String PATH = "/";
    private static final String ADD_USER = "/signup";
    @MockBean
    private UserServiceImpl userService;
    @Autowired
    private WebTestClient webClient;
   /* @BeforeEach
    void init(){
        mockMvc = webAppContextSetup(ctx).build();

    }*/

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

    /*@Test
    void getIndexPage() throws Exception {
        mockMvc.perform(post(PATH)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }*/
}