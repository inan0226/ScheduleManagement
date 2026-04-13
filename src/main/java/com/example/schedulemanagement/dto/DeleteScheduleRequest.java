package com.example.schedulemanagement.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeleteScheduleRequest {
    private final String password;


    public DeleteScheduleRequest(String password) {
        this.password = password;

    }
}
