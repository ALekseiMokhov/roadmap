package com.gmail.qwertygoog.roadmap.model;

import lombok.Data;

@Data
public class MessageEvent {
    private String to;
    private String from;
    private String subject;
    private String text;

}
