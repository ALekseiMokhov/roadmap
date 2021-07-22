package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.MessageEvent;
import com.gmail.qwertygoog.roadmap.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private static final String SEND_TEMPLATE = "mail";
    private static final String ERROR = "error";

    private final MailSenderService service;


    @PostMapping("/send")
    public Mono<String> sendMessage(@RequestParam MessageEvent event, Model model) {
        return service.sendMail(event)
                .flatMap(
                        sink -> {
                            return Mono.just(SEND_TEMPLATE);
                        }
                )
                .onErrorReturn(ERROR);
    }

}
