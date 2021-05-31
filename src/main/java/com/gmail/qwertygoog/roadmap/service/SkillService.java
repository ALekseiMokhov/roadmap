package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.Level;
import com.gmail.qwertygoog.roadmap.model.Priority;
import com.gmail.qwertygoog.roadmap.model.Skill;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SkillService {

    Flux<Skill> findAll();

    Flux<Skill> findAllByLevel(Level level);

    Flux<Skill> findAllByPriority(Priority priority);

    Mono<Skill> findById(UUID id);

    Mono<Skill> add(Skill skill);

    Mono<UUID> removeById(UUID id);

}
