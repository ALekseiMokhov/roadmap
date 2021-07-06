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

    private final SkillService service;

    @PostMapping("/add")
    public Mono<String> addSkill(@ModelAttribute Skill skill, Model model) {
        log.info("skill " + skill.getName() + " has been added!");
        return service.add(skill)
                .log("hook on flux")
                .flatMap(sink -> Mono.just(model.addAttribute(TEMPLATE, new ReactiveDataDriverContextVariable(service.findAll(), 10))))
                .log("another hook")
                .then(Mono.just(TEMPLATE));

    }

    @GetMapping("")
    public Mono<String> getAll(Model model) {
        return Mono.just(model)
                .flatMap(m -> {
                    m.addAttribute("skills", new ReactiveDataDriverContextVariable(service.findAll(), 10));
                    m.addAttribute("skill", new Skill());
                    return Mono.just(TEMPLATE);

                }).onErrorReturn(ERROR);
    }
    @GetMapping("/lvl")
    public Mono<String> getAllByLvl(@RequestParam Skill  lvl, Model model) {
        return Mono.just(model)
                .doOnNext(e -> log.info(lvl.toString()))
                .flatMap(m -> {

                    m.addAttribute("skill",new Skill());
                /*    m.addAttribute("skills",new ReactiveDataDriverContextVariable(service.findAllByLevel(Level.valueOf(level))));*/
                    return Mono.just(TEMPLATE);
                })
                .doOnError(e -> System.out.println(e.getCause() +"+++++++++++++++++++++++++++++++++++"))
                .onErrorReturn(ERROR);
    }

    @GetMapping("/priority")
    public String getAllByPriority(@ModelAttribute Priority priority, Model model) {
        service.findAllByPriority(priority);
        return TEMPLATE;
    }

    @GetMapping("/get")
    public String getSkill(@ModelAttribute UUID id, Model model) {
        IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll(), 1);
        model.addAttribute("skill", driverContextVariable);
        return TEMPLATE;
    }

}
