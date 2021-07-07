package com.gmail.qwertygoog.roadmap.model;

import lombok.Data;

@Data
public class MessageEvent {
    private final String to;
    private final String from;
    private final String subject;
    private final String text;

}
