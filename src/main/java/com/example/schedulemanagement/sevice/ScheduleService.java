package com.example.schedulemanagement.sevice;

import com.example.schedulemanagement.Exception.InvalidPasswordException;
import com.example.schedulemanagement.Exception.ScheduleNotFoundException;
import com.example.schedulemanagement.dto.*;
import com.example.schedulemanagement.entity.Schedule;
import com.example.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getName(),
                request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getName(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getSchedule(String author) {
        List<Schedule> schedules = scheduleRepository.findAllByAuthorOrderByModifiedAtDesc(author);
        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getName(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    public PatchScheduleResponse updateSchedule(UpdateScheduleRequest request, long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException());
        boolean isMatch = schedule.getPassword().equals(request.getPassword());
        if (!isMatch) {
            throw new InvalidPasswordException();
        }
        String newAuthor = request.getAuthor();
        String newTitle = request.getTitle();
        if (newAuthor.isBlank() && newTitle.isBlank()) {
            throw new IllegalArgumentException();
        }
        schedule.updateSchedule(newAuthor, newTitle);

        return new PatchScheduleResponse(
                schedule.getId(),
                schedule.getName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    public void deleteSchedule(DeleteScheduleRequest request, long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException());
        boolean isMatch = schedule.getPassword().equals(request.getPassword());
        if (!isMatch) {
            throw new InvalidPasswordException();
        }
        scheduleRepository.delete(schedule);
    }
}
