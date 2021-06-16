package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.Level;
import com.gmail.qwertygoog.roadmap.model.Priority;
import com.gmail.qwertygoog.roadmap.model.Skill;
import com.gmail.qwertygoog.roadmap.service.SkillService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import java.util.UUID;

@Controller
@RequestMapping("/skill")
@AllArgsConstructor
@Slf4j
public class SkillController {

    private static final String TEMPLATE ="skills";

    private final SkillService service;

    @PostMapping("/add")
    public String addSkill(@RequestBody Skill skill) {
        service.add(skill);
        return TEMPLATE;
    }


    @PostMapping("/remove")
    public String removeSkill(@RequestParam UUID id) {
        service.removeById(id);
        return TEMPLATE;
    }

    @GetMapping("/get")
    public String getAll(final Model model) {
        IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll());
        model.addAttribute(TEMPLATE, driverContextVariable);
        return TEMPLATE;
    }

    @GetMapping("/get/{level}")
    public String getAllByLvl(@RequestParam Level level) {
        service.findAllByLevel(level);
        return TEMPLATE;
    }

    @GetMapping("/get/{priority}")
    public String getAllByPriority(@RequestParam Priority priority) {
        service.findAllByPriority(priority);
        return TEMPLATE;
    }

    @GetMapping("/get/{id}")
    public String getSkill(@RequestParam UUID id, final Model model) {
        IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll(), 1);
        model.addAttribute("skill", driverContextVariable);
        return TEMPLATE;
    }

}
