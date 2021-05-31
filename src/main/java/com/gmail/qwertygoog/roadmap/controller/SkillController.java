package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.Level;
import com.gmail.qwertygoog.roadmap.model.Priority;
import com.gmail.qwertygoog.roadmap.model.Skill;
import com.gmail.qwertygoog.roadmap.service.SkillService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/skill")
@AllArgsConstructor
@Slf4j
public class SkillController {

    private final SkillService service;

    @PostMapping("/add")
    public Mono<Skill> addSkill(@RequestBody Skill skill) {
        return service.add(skill);
    }


    @PostMapping("/remove")
    public Mono<UUID> removeSkill(@RequestParam UUID id) {
        return service.removeById(id);
    }

    @GetMapping("/get")
    public Flux<Skill> getAll() {
        return service.findAll();
    }

    @GetMapping("/get/{level}")
    public Flux<Skill> getAllByLvl(@RequestParam Level level) {
        return service.findAllByLevel(level);
    }

    @GetMapping("/get/{priority}")
    public Flux<Skill> getAllByPriority(@RequestParam Priority priority) {
        return service.findAllByPriority(priority);
    }

    @GetMapping("/get/{id}")
    public Mono<Skill> getSkill(@RequestParam UUID id) {
        return service.findById(id);
    }

}
