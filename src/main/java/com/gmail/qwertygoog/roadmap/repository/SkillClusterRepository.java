package com.gmail.qwertygoog.roadmap.repository;

import com.gmail.qwertygoog.roadmap.model.SkillCluster;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface SkillClusterRepository extends ReactiveCrudRepository<SkillCluster, UUID> {

    Mono<SkillCluster> getByName(String name);

    Mono<SkillCluster> getById(UUID id);

    Mono<UUID> removeById(UUID id);
}
