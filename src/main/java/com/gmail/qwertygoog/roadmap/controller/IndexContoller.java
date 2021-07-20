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
    private static final String SEND_TEMPLATE = "mail";
    private static final String ERROR = "error";

    private final MailSenderService service;

    @PostMapping("/mail")
    public Mono<String> redirectToSend() {
        return Mono.just(SEND_TEMPLATE);
    }

    @PostMapping("/send")
    public Mono<String> sendMessage(@RequestParam MessageEvent event, Model model) {
        return service.sendMail(event)
                .flatMap(
                        sink -> {
                            return Mono.just(TEMPLATE);
                        }
                )
                .onErrorReturn(ERROR);
    }

    @GetMapping("/")
    public Mono<String> greet(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Hello, %s", name));
    }
}
