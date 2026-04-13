package com.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private final String name;
    private final String title;
    private final String content;
    private final String author;
    private final String password;

    public CreateScheduleRequest(String name, String title, String content, String author, String password) {
        this.name = name;
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }
}
