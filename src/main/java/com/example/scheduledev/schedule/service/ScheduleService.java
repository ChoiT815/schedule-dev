package com.example.scheduledev.schedule.service;

import com.example.scheduledev.schedule.domain.Schedule;
import com.example.scheduledev.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 생성(C)
    public Schedule createSchedule(String title, String content, String author) {
        Schedule schedule = new Schedule(title, content, author);
        return scheduleRepository.save(schedule);
    }

    // 전체 조회(R)
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    // 단건 조회(R)
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
    }

    // 수정(U)
    @Transactional
    public Schedule updateSchedule(Long id, String title, String content) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(   () -> new RuntimeException("해당 일정이 없습니다."));
        schedule.update(title, content);
        return schedule;
    }

    // 삭제(D)
    @Transactional
    public Schedule deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
        scheduleRepository.delete(schedule);
        return schedule;
    }
}
