package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.MessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {
    private final JavaMailSender sender;

    @Override
    public Mono<Void> sendMail(MessageEvent event) {
        return Mono.fromCallable(() -> {
            process(event.getTo(), event.getFrom(), event.getSubject(), event.getText());
            return Mono.empty();
        })
                .then();
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
