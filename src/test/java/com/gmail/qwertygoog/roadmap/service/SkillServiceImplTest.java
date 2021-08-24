package com.gmail.qwertygoog.roadmap.service;

import com.gmail.qwertygoog.roadmap.model.Level;
import com.gmail.qwertygoog.roadmap.model.Priority;
import com.gmail.qwertygoog.roadmap.model.Skill;
import com.gmail.qwertygoog.roadmap.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class SkillServiceImplTest {
    @Autowired
    private SkillService skillService;
    @MockBean
    private SkillRepository repository;

    @Test
    void testServiceAddSkill() {
        skillService.add(new Skill());
        Mockito.verify(repository, Mockito.times(1)).save(any());
    }

    @Test
    void testServiceFindByPriority() {
        skillService.findAllByPriority(Priority.HIGH);
        Mockito.verify(repository, Mockito.times(1)).findAllByPriority(Priority.HIGH);
    }

    @Test
    void testServiceFindByLvl() {
        skillService.findAllByLevel(Level.A);
        Mockito.verify(repository, Mockito.times(1)).findAllByLevel(Level.A);
    }

    @Test
    void testServiceFindAll() {
        skillService.findAll();
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    void testServiceFindById() {
        skillService.findById(UUID.randomUUID());
        Mockito.verify(repository, Mockito.times(1)).findById(any(UUID.class));
    }
    @Test
    void testServiceRemoveById() {
        skillService.removeById(UUID.randomUUID());
        Mockito.verify(repository, Mockito.times(1)).removeById(any(UUID.class));
    }
    @Test
    void testServiceRemoveByName() {
        skillService.removeByName("name");
        Mockito.verify(repository, Mockito.times(1)).deleteByName("name");
    }

}