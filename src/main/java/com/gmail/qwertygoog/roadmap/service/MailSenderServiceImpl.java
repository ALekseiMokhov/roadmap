package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender sender;

    @Override
    public Mono<Void> sendMail(MessageEvent event) {
        try {
            process(event.getTo(), event.getFrom(), event.getSubject(), event.getText());
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
        return Mono.empty();
    }

    private void process(String to, String from, String subject, String text) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText(text);
        helper.setSubject(subject);
    }
}
