package com.gmail.qwertygoog.roadmap;

import com.gmail.qwertygoog.roadmap.security.PropertyEncryptConfig;
import com.gmail.qwertygoog.roadmap.security.SecurityConfig;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableEncryptableProperties
@Import({SecurityConfig.class, PropertyEncryptConfig.class})
public class RoadmapApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoadmapApplication.class, args);
}

}
