package com.gmail.qwertygoog.roadmap.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum Level {

    A("A"),
    B("B"),
    C("C"),
    D("D");

    private final String val;

    Level(String val) {
        this.val = val;
    }

    @JsonCreator
    public static Level getVal(String v) {
        return Level.valueOf(v);
    }
}
