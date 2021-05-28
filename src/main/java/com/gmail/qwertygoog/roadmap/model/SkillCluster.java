package com.gmail.qwertygoog.roadmap.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Data
public class SkillCluster {
    @Id
    private UUID id;
    private String name;
    private Flux<SkillGroup> skillGroups;
}
