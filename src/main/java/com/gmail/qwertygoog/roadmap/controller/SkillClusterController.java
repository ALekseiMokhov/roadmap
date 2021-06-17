package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.SkillCluster;
import com.gmail.qwertygoog.roadmap.service.SkillClusterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import java.util.UUID;

@Controller
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class SkillClusterController {
    private static final String TEMPLATE = "skill_cluster";
    private final SkillClusterService service;

    @PostMapping("/add")
    public String addSkillCluster(@RequestParam String name, ModelMap model) {
        SkillCluster cluster = new SkillCluster(null, name);
        service.add(cluster);
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(service.findAll(), 1);
        model.addAttribute("clusters", reactiveDataDrivenMode);
        return TEMPLATE;
    }

    @PostMapping("/remove")
    public String removeSkillCluster(@RequestParam UUID id) {
        service.removeById(id);
        return TEMPLATE;
    }

    @GetMapping("/")
    public String getAll(final Model model) {
        IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll());
        model.addAttribute("clusters", driverContextVariable);
        return TEMPLATE;
    }

    @GetMapping("/get/{id}")
    public String getSkillCluster(@RequestParam UUID id, final Model model) {
        IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll(), 1);
        model.addAttribute("cluster", driverContextVariable);
        return TEMPLATE;
    }

}
