package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.SkillGroup;
import com.gmail.qwertygoog.roadmap.repository.SkillGroupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class SkillGroupServiceImpl implements SkillGroupService{

    private final SkillGroupRepository repository;

    @Override
    public Flux<SkillGroup> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<SkillGroup> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Mono<SkillGroup> add(SkillGroup skillGroup) {
        return repository.save(skillGroup);
    }
}
