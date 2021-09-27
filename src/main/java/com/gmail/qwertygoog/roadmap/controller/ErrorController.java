package com.gmail.qwertygoog.roadmap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/")
public class ErrorController {
    @RequestMapping("/error")
    public Mono<String> redirectError(){
        return Mono.just("error");
    }
}
