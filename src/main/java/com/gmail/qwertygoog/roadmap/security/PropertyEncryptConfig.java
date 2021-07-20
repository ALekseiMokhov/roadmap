package com.gmail.qwertygoog.roadmap.security;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EncryptablePropertySource(name = "EncryptedProperties",value = "classpath:application.properties")
/*@PropertySource("classpath:com.qwertygoog.gmail.roadmap.admin.password")
@PropertySource("spring.mail.username")
@PropertySource("spring.mail.password")
@PropertySource("spring.r2dbc.password")*/
public class PropertyEncryptConfig {
}
