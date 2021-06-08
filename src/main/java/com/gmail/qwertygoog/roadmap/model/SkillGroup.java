package com.gmail.qwertygoog.roadmap.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table
public class SkillGroup {
    @Id
    private UUID id;
    @Column("skill_cluster_id")
    private UUID clusterId;
    @Column("skill_group_name")
    private String name;

}
