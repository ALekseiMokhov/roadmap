package com.gmail.qwertygoog.roadmap.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Skill {
    @Id
    private UUID id;
    private String name;
    private Level level;
    private Priority priority;
}
