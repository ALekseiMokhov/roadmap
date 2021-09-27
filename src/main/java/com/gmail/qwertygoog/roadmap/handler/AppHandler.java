package com.gmail.qwertygoog.roadmap.handler;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@ControllerAdvice
@Slf4j
public class AppHandler {

    @ExceptionHandler(RuntimeException.class)
    public Mono<String> handleException(@NotNull RuntimeException e){
        log.error("Caught RuntimeException : " + e.getLocalizedMessage());
        return Mono.just("error");
    }
     @ExceptionHandler(WebExchangeBindException.class)
    public Mono<String> getWebExchangeBindException(@NotNull WebExchangeBindException e) {
        log.error("Caught WebExchangeBindException: " + e.getLocalizedMessage());
        return Mono.just("error");
    }

}
