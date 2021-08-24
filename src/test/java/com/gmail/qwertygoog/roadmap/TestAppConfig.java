package com.gmail.qwertygoog.roadmap;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(mode = AdviceMode.PROXY)

public class TestAppConfig {
}
