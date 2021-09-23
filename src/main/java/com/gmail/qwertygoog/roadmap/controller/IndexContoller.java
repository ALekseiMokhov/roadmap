package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class IndexContoller {
    private final UserServiceImpl userService;

    @GetMapping("")
    public Mono<String> getIndexPage() {
        return Mono.just("index");
    }

}
