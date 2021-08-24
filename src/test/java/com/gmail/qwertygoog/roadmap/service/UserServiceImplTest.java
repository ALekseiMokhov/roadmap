package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.User;
import com.gmail.qwertygoog.roadmap.model.UserRole;
import com.gmail.qwertygoog.roadmap.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @MockBean
    private UserRepo userRepo;
    private User user;

    @BeforeEach
    void init() {
        user = new User();
        user.setUsername("Ivan");
        user.setRole(UserRole.ROLE_USER);
    }

    @Test
    void findByUsername() {
        when(userRepo.findByUsername(any())).thenReturn(Mono.just(user));
        Mono<UserDetails> res = userService.findByUsername("Ivan");
        assertNotNull(res.block());
    }

    @Test
    void save() {
        when(userRepo.save(any())).thenReturn(Mono.just(user));
        Mono<User> res = userService.save(user);
        User persisted = res.block();
        assertNotNull(persisted);
        assertEquals("Ivan", persisted.getUsername());
    }
}