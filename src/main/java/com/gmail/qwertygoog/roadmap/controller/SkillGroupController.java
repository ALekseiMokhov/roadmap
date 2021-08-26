package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.SkillGroup;
import com.gmail.qwertygoog.roadmap.service.SkillGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/group")
@AllArgsConstructor
@Slf4j
public class SkillGroupController {
    private static final String TEMPLATE = "skill_group";
    private static final String ERROR = "error";
    private static final String ATTRIBUTE_GROUP = "group";
    private static final String ATTRIBUTE_GROUPS = "groups";
    private final SkillGroupService service;


    @PostMapping("/add")
    public Mono<String> addSkillGroup(@ModelAttribute SkillGroup group, Model model) {
        log.info("group is: " + group.getName());
        return service.add(group)
                .flatMap(
                        sink-> {
                            model.addAttribute(ATTRIBUTE_GROUP, group);
                            model.addAttribute(ATTRIBUTE_GROUPS, new ReactiveDataDriverContextVariable(service.findAll(), 10));
                            return Mono.just(TEMPLATE);
                        }
                ).onErrorReturn(ERROR);
    }

    @PostMapping("/delete")
    public Mono<String> removeSkillGroup(@ModelAttribute SkillGroup group, Model model) {

        return service.removeByName(group)
                .flatMap(m -> {
                    model.addAttribute(ATTRIBUTE_GROUP, new SkillGroup());
                    model.addAttribute(ATTRIBUTE_GROUPS, new ReactiveDataDriverContextVariable(service.findAll(), 100));
                    return Mono.just(TEMPLATE);
                }).onErrorReturn(ERROR);

    }

    @GetMapping("")
    public Mono<String> getAll(Model model) {
        return Mono.just(model)
                .flatMap(m -> {
                    model.addAttribute(ATTRIBUTE_GROUPS, new ReactiveDataDriverContextVariable(service.findAll(), 100));
                    model.addAttribute(ATTRIBUTE_GROUP, new SkillGroup());
                    return Mono.just(TEMPLATE);
                }).onErrorReturn(ERROR);
    }

}
