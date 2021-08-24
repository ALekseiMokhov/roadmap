package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.MessageEvent;
import com.gmail.qwertygoog.roadmap.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mail")
public class MailController {

    private static final String MAIL_TEMPLATE = "mail";
    private static final String SEND_TEMPLATE = "index";
    private static final String ERROR = "error";
    private static final String CONTACT_SUBJ = "Contact from your site";
    @Value("${spring.mail.username}")
    private String to;

    private final MailSenderService service;

    @GetMapping("")
    public Mono<String> render(Model model) {
        model.addAttribute(new MessageEvent());
        return Mono.just(MAIL_TEMPLATE);
    }

    @PostMapping("/send")
    public Mono<String> sendMessage(@ModelAttribute MessageEvent event, Model model) throws MessagingException {
        event.setSubject(CONTACT_SUBJ);
        event.setTo(to);
        log.info("Email with topic {} was sent from {} successfully!",event.getSubject(),event.getFrom());
        service.sendMail(event);
        return  Mono.just(SEND_TEMPLATE)
                .onErrorReturn(ERROR);
    }

}
