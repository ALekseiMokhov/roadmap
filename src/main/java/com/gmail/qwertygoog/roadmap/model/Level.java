package com.gmail.qwertygoog.roadmap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {
    BEGINNER(1),
    INTERMEDIATE(2),
    PROFICIENT(3),
    ADVANCED(4);
    private int point;
}
