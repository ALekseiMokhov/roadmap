package com.gmail.qwertygoog.roadmap.controller;

import com.gmail.qwertygoog.roadmap.model.SkillCluster;
import com.gmail.qwertygoog.roadmap.service.SkillClusterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/cluster")
public class SkillClusterController {
    private static final String TEMPLATE = "skill_cluster";
    private final SkillClusterService service;

/*    @RequestMapping("/")
    public Mono<String> processRequest( BindingResult bindingResult, ServerWebExchange exchange) {
        SkillCluster cluster = new SkillCluster();
        service.add(cluster);
        return exchange.getFormData().flatMap(
                data -> {
                    if (data.containsKey("save")) {
                        return saveCluster( bindingResult);
                    }
                    if (data.containsKey("addRow")) {
                        return addRow( bindingResult);
                    }
                    if (data.containsKey("removeRow")) {
                        UUID id = UUID.fromString(data.getFirst("removeRow"));
                        return removeSkillCluster( bindingResult, id);
                    }
                }
        );
    }*/

   /* @PostMapping(value="/add", params={"save"})*/
    public String saveCluster( SkillCluster skillCluster ,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return TEMPLATE;
        }
        this.service.add(skillCluster);
        return "redirect:/" +TEMPLATE;
    }

    public String removeSkillCluster(BindingResult bindingResult, @RequestParam UUID id) {
        service.removeById(id);
        return TEMPLATE;
    }
 @RequestMapping(value="")
    public String getAll(final Model model) {
        IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll(),100);
        model.addAttribute("clusters", driverContextVariable);
        return TEMPLATE;
    }

    public String getSkillCluster(@RequestParam UUID id) {
        IReactiveDataDriverContextVariable driverContextVariable = new ReactiveDataDriverContextVariable(service.findAll(), 1);
        return TEMPLATE;
    }

}
