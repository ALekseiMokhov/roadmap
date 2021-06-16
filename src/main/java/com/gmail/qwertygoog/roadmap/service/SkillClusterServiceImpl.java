package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.SkillCluster;
import com.gmail.qwertygoog.roadmap.repository.SkillClusterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Service
@AllArgsConstructor
public class SkillClusterServiceImpl implements SkillClusterService{

    private final SkillClusterRepository repository;

    @Override
    public Flux<SkillCluster> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<SkillCluster> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Mono<UUID> removeById(UUID id) {
        return repository.removeById(id);
    }

    @Override
    public Mono<SkillCluster> add(SkillCluster cluster) {
        return repository.save(cluster);
    }
}
