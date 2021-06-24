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

import java.util.UUID;

@Controller
@RequestMapping("/group")
@AllArgsConstructor
@Slf4j
public class SkillGroupController {
    private static final String TEMPLATE = "skill_group";
    private final SkillGroupService service;


    @PostMapping("/add")
    public String addSkillCluster(@RequestBody SkillGroup group) {
        service.add(group);
        return TEMPLATE;
    }

    @GetMapping("")
    public String getAll(final Model model) {
        IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll(),100);
        model.addAttribute("groups", driverContextVariable);
        return TEMPLATE;
    }

    @GetMapping("/get/{id}")
    public String getSkillCluster(@RequestParam UUID id, final Model model) {
        IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll(), 1);
        model.addAttribute("group", driverContextVariable);
        return TEMPLATE;
    }
}
