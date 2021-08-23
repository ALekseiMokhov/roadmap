package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.MessageEvent;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;

public interface MailSenderService {

    Mono<Void>sendMail(MessageEvent event) throws MessagingException;
}
