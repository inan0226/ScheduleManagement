package com.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private final String author;
    private final String title;
    private final String password;

    public UpdateScheduleRequest(String author, String title, String password) {
        this.author = author;
        this.title = title;
        this.password = password;
    }
}
