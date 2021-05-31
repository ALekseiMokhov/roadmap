package com.gmail.qwertygoog.roadmap.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Data
@Table
public class SkillGroup {
    @Id
    private UUID id;
    private String name;
    private Flux<Skill> skills;
}
