package com.gmail.qwertygoog.roadmap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoadmapApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertDoesNotThrow(() -> System.out.println("CONTAINER IS RUNNING!"));
    }

}
