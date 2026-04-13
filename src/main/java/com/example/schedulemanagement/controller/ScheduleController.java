package com.example.schedulemanagement.controller;

import com.example.schedulemanagement.Exception.InvalidPasswordException;
import com.example.schedulemanagement.Exception.ScheduleNotFoundException;
import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.sevice.ScheduleSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getSchedule(@RequestParam String author) {
        List<GetScheduleResponse> result = scheduleSevice.getSchedule(author);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/schedules/{id}")
    public ResponseEntity<PatchScheduleResponse> updateSchedule(@RequestBody UpdateScheduleRequest request, long id) {
        try {
            PatchScheduleResponse result = scheduleSevice.updateSchedule(request, id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (ScheduleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidPasswordException e) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }


}
