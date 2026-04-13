package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.Exception.InvalidPasswordException;
import com.example.schedulemanagement.Exception.ScheduleNotFoundException;
import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.sevice.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request
    ) {
        CreateScheduleResponse result = scheduleService.createSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getSchedule(@RequestParam String author) {
        List<GetScheduleResponse> result = scheduleService.getSchedule(author);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/schedules/{id}")
    public ResponseEntity<PatchScheduleResponse> updateSchedule(@RequestBody UpdateScheduleRequest request, long id) {
        try {
            PatchScheduleResponse result = scheduleService.updateSchedule(request, id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (ScheduleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@RequestBody DeleteScheduleRequest request, long id) {
        try {
            scheduleService.deleteSchedule(request, id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ScheduleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }


}
