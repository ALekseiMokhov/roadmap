package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.MessageEvent;
import com.gmail.qwertygoog.roadmap.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexContoller {

    private static final String TEMPLATE = "index";

    @PostMapping("/")
    public Mono<String> greet(Mono<Principal> principal, Model model) {
        return principal
                .map(Principal::getName)
/*
                .map(name -> model.addAttribute("user", name))
*/
                .then(Mono.just(TEMPLATE));
    }
}
