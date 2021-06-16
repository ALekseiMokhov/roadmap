package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.SkillGroup;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SkillGroupService {

    Flux<SkillGroup> findAll();

    Mono<SkillGroup> findById(UUID id);

    Mono<SkillGroup> add(SkillGroup skillGroup);
}
