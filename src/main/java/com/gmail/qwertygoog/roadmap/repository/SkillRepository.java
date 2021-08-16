package com.gmail.qwertygoog.roadmap.repository;

import com.gmail.qwertygoog.roadmap.model.Level;
import com.gmail.qwertygoog.roadmap.model.Priority;
import com.gmail.qwertygoog.roadmap.model.Skill;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface SkillRepository extends ReactiveCrudRepository<Skill, UUID> {

    Mono<UUID> removeById(UUID id);

    Mono<String> deleteByName(String name);

    Flux<Skill> findAllByLevel(Level level);

    Flux<Skill> findAllByPriority(Priority priority);

    Mono<Skill> findByName(String name);

    Mono<Boolean> existsSkillByName(String name);
}
