package com.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String name;
    private String content;
    private String author;
    private String password;
}
