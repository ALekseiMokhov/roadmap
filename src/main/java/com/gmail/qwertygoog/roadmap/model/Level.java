package com.gmail.qwertygoog.roadmap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {
    A(1),
    B(2),
    C(3),
    D(4);
    private int point;
}
