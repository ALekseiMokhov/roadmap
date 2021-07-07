package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.SkillGroup;
import com.gmail.qwertygoog.roadmap.service.SkillGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@RequestMapping("/group")
@AllArgsConstructor
@Slf4j
public class SkillGroupController {
    private static final String TEMPLATE = "skill_group";
    private static final String ERROR = "error";
    private final SkillGroupService service;


    @PostMapping("/add")
    public Mono<String> addSkillGroup(@ModelAttribute SkillGroup group, Model model) {
        log.info("group is: " + group.getName());
        return Mono.just(model)
                .flatMap(
                        m -> {
                            service.add(group);
                            m.addAttribute("group", group);
                            m.addAttribute("groups", new ReactiveDataDriverContextVariable(service.findAll(), 10));
                            return Mono.just(TEMPLATE);
                        }
                ).onErrorReturn(ERROR);
    }

    @PostMapping("/delete")
    public Mono<String> removeSkillGroup(@ModelAttribute SkillGroup group, Model model) {

        return Mono.just(model)
                .flatMap(m -> {
                    service.removeByName(group);
                    model.addAttribute("groups", new ReactiveDataDriverContextVariable(service.findAll(), 100));
                    return Mono.just(TEMPLATE);
                }).onErrorReturn(ERROR);

    }

    @GetMapping("")
    public Mono<String> getAll(Model model) {
        return Mono.just(model)
                .flatMap(m -> {
                    model.addAttribute("groups", new ReactiveDataDriverContextVariable(service.findAll(), 100));
                    model.addAttribute("group", new SkillGroup());
                    return Mono.just(TEMPLATE);
                }).onErrorReturn(ERROR);
    }

}
