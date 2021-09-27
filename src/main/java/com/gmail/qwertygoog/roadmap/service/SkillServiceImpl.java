package com.gmail.qwertygoog.roadmap.service;

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
    public Flux<Skill> findAllByLevel(int level) {
        return repository.findAllByLevel(level);
    }

    @Override
    public Flux<Skill> findAllByPriority(Priority priority) {
        return repository.findAllByPriority(priority);
    }

    @Override
    public Mono<Skill> findById(UUID id) {
        return repository.findById(id);
    }

    @Override

    public Mono<Skill> add(Skill skill) {
        return repository.save(skill);
    }

    @Override
    public Mono<UUID> removeById(UUID id) {
        return repository.removeById(id);
    }

    @Override
    public Mono<String> removeByName(String name) {
        return repository.deleteByName(name);
    }

    @Override
    public Mono<Skill> findByName(String name) {
        return repository.findByName(name);
    }

}
