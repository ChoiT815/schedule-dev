package com.example.scheduledev.schedule.controller;

import com.example.scheduledev.schedule.dto.ScheduleRequestDto;
import com.example.scheduledev.schedule.dto.ScheduleResponseDto;
import com.example.scheduledev.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 생성(C)
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return new ScheduleResponseDto(
                scheduleService.createSchedule(
                        requestDto.getTitle(),
                        requestDto.getContent(),
                        requestDto.getAuthor()
                )
        );
    }

    // 전체 조회(R)
    @GetMapping
    public List<ScheduleResponseDto> getSchedule() {
        return scheduleService.getSchedules().stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    // 단건 조회(R)
    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return new ScheduleResponseDto(scheduleService.getSchedule(id));
    }

    // 수정(U)
    @PatchMapping("/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return new ScheduleResponseDto(
                scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContent())
        );
    }

    // 삭제(D)
    @DeleteMapping("/{id}")
    public ScheduleResponseDto deleteSchedule(@PathVariable Long id) {
        return new ScheduleResponseDto(
                scheduleService.deleteSchedule(id)
        );
    }






}
