package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.SkillCluster;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SkillClusterService {
    Flux<SkillCluster> findAll();
    /*Flux<>*/
    Mono<SkillCluster> findById(UUID id);

    Mono<UUID> removeById(UUID id);

    Mono<SkillCluster> add(SkillCluster cluster);

}
