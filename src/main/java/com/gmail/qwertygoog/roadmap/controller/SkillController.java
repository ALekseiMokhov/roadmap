package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.Level;
import com.gmail.qwertygoog.roadmap.model.Priority;
import com.gmail.qwertygoog.roadmap.model.Skill;
import com.gmail.qwertygoog.roadmap.service.SkillService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@RequestMapping("/skill")
@AllArgsConstructor
@Slf4j
public class SkillController {

    private static final String TEMPLATE = "skills";
    private static final String ERROR = "error";
    private static final String ATTRIBUTE_SKILL = "skill";
    private static final String ATTRIBUTE_SKILLS = "skills";


    private final SkillService service;

    @PostMapping("/add")
    public Mono<String> addSkill(@ModelAttribute Skill skill, Model model) {
        return service.add(skill)
                .flatMap(m -> {
                            model.addAttribute(ATTRIBUTE_SKILLS, new ReactiveDataDriverContextVariable(service.findAll(), 10));
                            model.addAttribute(ATTRIBUTE_SKILL, new Skill());
                            return Mono.just(TEMPLATE);
                        }
                )
                .onErrorReturn(ERROR);

    }

    @GetMapping("")
    public Mono<String> getAll(Model model) {
        return Mono.just(model)
                .flatMap(m -> {
                    m.addAttribute(ATTRIBUTE_SKILLS, new ReactiveDataDriverContextVariable(service.findAll(), 10));
                    m.addAttribute(ATTRIBUTE_SKILL, new Skill());
                    return Mono.just(TEMPLATE);

                }).onErrorReturn(ERROR);
    }

    @PostMapping("/lvl")
    public Mono<String> getAllByLvl(@ModelAttribute Skill skillByLvl, Model model) {

        return Mono.just(model)
                .flatMap(m -> {
                    model.addAttribute(ATTRIBUTE_SKILL, new Skill());
                    model.addAttribute(ATTRIBUTE_SKILLS, new ReactiveDataDriverContextVariable(service.findAllByLevel(skillByLvl.getLevel())));
                    return Mono.just(TEMPLATE);
                })
                .onErrorReturn(ERROR);
    }

    @PostMapping("/priority")
    public Mono<String> getAllByPriority(@ModelAttribute Skill skill, Model model) {
        return Mono.just(model)
                .flatMap(m -> {
                    model.addAttribute(ATTRIBUTE_SKILL, new Skill());
                    model.addAttribute(ATTRIBUTE_SKILLS, new ReactiveDataDriverContextVariable(service.findAllByPriority(skill.getPriority())));
                    return Mono.just(TEMPLATE);
                }).onErrorReturn(ERROR);
    }

    @PostMapping("/get")
    public Mono<String> getSkill(@ModelAttribute Skill skill, Model model) {
        return Mono.just(model)
                .flatMap(m -> {
                    IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findByName(skill.getName()).flux(), 1);
                    model.addAttribute(ATTRIBUTE_SKILL, new Skill());
                    model.addAttribute(ATTRIBUTE_SKILLS, driverContextVariable);
                    return Mono.just(TEMPLATE);
                }).onErrorReturn(ERROR);
    }

    @PostMapping("/delete")
    public Mono<String> deleteSkill(@ModelAttribute Skill skill, Model model) {
        return Mono.just(model)
                .flatMap(
                        m -> {
                            service.removeByName(skill.getName());
                            IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll());
                            model.addAttribute(ATTRIBUTE_SKILL, new Skill());
                            model.addAttribute(ATTRIBUTE_SKILLS, driverContextVariable);
                            return Mono.just(TEMPLATE);
                        }
                ).onErrorReturn(ERROR);
    }
}
