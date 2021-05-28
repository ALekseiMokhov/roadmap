package com.gmail.qwertygoog.roadmap.repository;

import com.gmail.qwertygoog.roadmap.model.Skill;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface SkillRepository extends ReactiveCrudRepository<Skill, UUID> {

    Mono<UUID> removeById(UUID id);
}
