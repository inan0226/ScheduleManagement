package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.dto.CreateScheduleRequest;
import com.example.schedulemanagement.dto.CreateScheduleResponse;
import com.example.schedulemanagement.repository.ScheduleRepository;
import com.example.schedulemanagement.sevice.ScheduleSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.CacheResponse;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleSevice scheduleSevice;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request
    ) {
        CreateScheduleResponse result = scheduleSevice.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
