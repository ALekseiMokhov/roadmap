package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.Level;
import com.gmail.qwertygoog.roadmap.model.Priority;
import com.gmail.qwertygoog.roadmap.model.Skill;
import com.gmail.qwertygoog.roadmap.repository.SkillRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class SkillServiceImpl implements SkillService {

    private final SkillRepository repository;

    @Override
    public Flux<Skill> findAll() {
        return repository.findAll();
    }

    @Override
    public Flux<Skill> findAllByLevel(Level level) {
        return null;
    }

    @Override
    public Flux<Skill> findAllByPriority(Priority priority) {
        return null;
    }

    @Override
    public Mono<Skill> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Skill> add(Skill skill) {
        log.debug("SKILL IS ______ "+ skill.getName() + "  ____WAS SAVED");
        return repository.save(skill);
    }

    @Override
    public Mono<UUID> removeById(UUID id) {
        return repository.removeById(id);
    }

}
