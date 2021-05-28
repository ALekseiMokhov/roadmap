package com.gmail.qwertygoog.roadmap.repository;

import com.gmail.qwertygoog.roadmap.model.SkillGroup;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface SkillGroupRepository extends ReactiveCrudRepository<SkillGroup, UUID> {

    Mono<SkillGroup> getByName(String name);

    Mono<SkillGroup> getById(UUID id);


    Mono<UUID> removeById(UUID id);
}
