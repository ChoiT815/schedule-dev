package com.example.scheduledev.schedule.controller;

import com.example.scheduledev.schedule.dto.ScheduleRequestDto;
import com.example.scheduledev.schedule.dto.ScheduleResponseDto;
import com.example.scheduledev.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @RestController: @Controller + @ResponseBody 합친 것
 * 모든 메서드의 반환값을 JSON으로 변환해서 응답
 * @RequestMapping: 이 Controller의 기본 URL을 "/schedules"로 지정
 */
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    /*========== 속성 ===========*/

    /* final: 한 번 주입받으면 변경 불가 (불변성 보장) */
    private final ScheduleService scheduleService; /* 일정 비즈니스 로직 담당 */

    /*========== 생성자 ===========*/

    /*
     * 생성자 주입 방식: @Autowired 없이 Spring이 자동으로 의존성 주입
     */
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /*========== 기능 ===========*/

    /*
     * 일정 생성 (POST /schedules)
     * @PostMapping: HTTP POST 요청을 이 메서드에 매핑
     * @RequestBody: HTTP 요청 Body의 JSON을 ScheduleRequestDto 객체로 변환
     */
    @PostMapping
    public ScheduleResponseDto createSchedules(@RequestBody ScheduleRequestDto requestDto) {
        return new ScheduleResponseDto(
                scheduleService.createSchedule(
                        requestDto.getTitle(),
                        requestDto.getContent(),
                        requestDto.getUserId()
                )
        );
    }

    /*
     * 전체 조회 (GET /schedules)
     * @GetMapping: HTTP GET 요청을 이 메서드에 매핑
     * 모든 일정을 List<ScheduleResponseDto>로 반환
     */
    @GetMapping
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleService.getSchedules().stream()
                .map(ScheduleResponseDto::new) /* Schedule → ScheduleResponseDto 변환 */
                .toList();
    }

    /*
     * 단건 조회 (GET /schedules/{id})
     * @PathVariable: URL 경로의 {id} 값을 Long id 파라미터로 받음
     */
    @GetMapping("/{id}")
    public ScheduleResponseDto getSchedules(@PathVariable Long id) {
        return new ScheduleResponseDto(scheduleService.getSchedule(id));
    }

    /*
     * 수정 (PATCH /schedules/{id})
     * @PatchMapping: HTTP PATCH 요청을 이 메서드에 매핑 (일부 수정)
     * PUT은 전체 수정, PATCH는 일부 수정에 사용
     */
    @PatchMapping("/{id}")
    public ScheduleResponseDto updateSchedules(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return new ScheduleResponseDto(
                scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContent())
        );
    }

    /*
     * 삭제 (DELETE /schedules/{id})
     * @DeleteMapping: HTTP DELETE 요청을 이 메서드에 매핑
     * 삭제된 일정을 응답으로 반환
     */
    @DeleteMapping("/{id}")
    public ScheduleResponseDto deleteSchedules(@PathVariable Long id) {
        return new ScheduleResponseDto(
                scheduleService.deleteSchedule(id)
        );
    }
}
