package com.gmail.qwertygoog.roadmap.security;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
@EncryptablePropertySource(value = "classpath:application.properties")
public class PropertyEncryptConfig {
}
