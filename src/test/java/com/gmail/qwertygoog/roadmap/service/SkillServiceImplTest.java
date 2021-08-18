package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.Skill;
import com.gmail.qwertygoog.roadmap.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class SkillServiceImplTest {
    @Autowired
    private SkillService skillService;
    @MockBean
    private SkillRepository repository;

    @Test
    void testServiceAddSkill(){
        skillService.add(new Skill());
        Mockito.verify(repository,Mockito.times(1)).save(any());
    }

}