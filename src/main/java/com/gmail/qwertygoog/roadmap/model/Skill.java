package com.gmail.qwertygoog.roadmap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Skill implements Persistable<UUID> {
    @Id
    private UUID id;
    @Column("skill_name")
    private String name;
    private Level level;
    private Priority priority;
    @Column("skill_group_name")
    private String skillGroupName;

    @Override
    public boolean isNew() {
        boolean result = Objects.isNull(id);
        this.id = result ? UUID.randomUUID() : this.id;
        return result;
    }
}
