package com.example.scheduledev.schedule.controller;

import com.example.scheduledev.schedule.dto.*;
import com.example.scheduledev.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 일정 관련 HTTP 요청을 처리하는 Controller
 * @RestController: @Controller + @ResponseBody 합친 것, 모든 메서드의 반환값을 JSON으로 변환해서 응답
 * @RequestMapping: 이 Controller의 기본 URL을 "/schedules"로 지정
 */
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    /*========== 속성 ===========*/

    /* final: 한 번 주입받으면 변경 불가 (불변성 보장) */
    private final ScheduleService scheduleService;

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
     * @RequestBody: HTTP 요청 Body의 JSON을 ScheduleCreateRequest 객체로 변환
     */
    @PostMapping
    public ResponseEntity<Object> createSchedules(@RequestBody ScheduleCreateRequest requestDto, HttpSession session) {
        /* 세션에서 userId를 꺼내 로그인 여부 확인 */
        Long userId = (Long) session.getAttribute("userId");

        /* 로그인 안 했으면 401 에러 반환 */
        if(userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        return ResponseEntity.ok(new ScheduleCreateResponse(
                scheduleService.createSchedule(
                        requestDto.getTitle(),
                        requestDto.getContent(),
                        userId /* 세션에서 가져온 userId 사용 */
                )
        ));
    }

    /*
     * 일정 전체 조회 (GET /schedules)
     * @GetMapping: HTTP GET 요청을 이 메서드에 매핑
     * 로그인 없이 누구나 조회 가능
     * 모든 일정을 List<ScheduleGetResponse>로 반환
     */
    @GetMapping
    public List<ScheduleGetResponse> getSchedules() {
        return scheduleService.getSchedules().stream()
                .map(ScheduleGetResponse::new)
                .toList();
    }

    /*
     * 일정 단건 조회 (GET /schedules/{id})
     * @GetMapping: HTTP GET 요청을 이 메서드에 매핑
     * @PathVariable: URL 경로의 {id} 값을 Long id 파라미터로 받음
     * 로그인 없이 누구나 조회 가능
     */
    @GetMapping("/{id}")
    public ScheduleGetResponse getSchedules(@PathVariable Long id) {
        return new ScheduleGetResponse(scheduleService.getSchedule(id));
    }

    /*
     * 일정 수정 (PATCH /schedules/{id})
     * @PatchMapping: HTTP PATCH 요청을 이 메서드에 매핑 (일부 수정)
     * PUT은 전체 수정, PATCH는 일부 수정에 사용
     * 로그인 필요 - 세션에서 userId를 꺼내 로그인 여부 확인, null이면 401 반환
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateSchedules(@PathVariable Long id, @RequestBody ScheduleUpdateRequest requestDto, HttpSession session) {
        /* 세션에서 userId를 꺼내 로그인 여부 확인 */
        Long userId = (Long) session.getAttribute("userId");

        /* 로그인 안 했으면 401 에러 반환 */
        if(userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        return ResponseEntity.ok(new ScheduleUpdateResponse(
                scheduleService.updateSchedule(id, requestDto.getTitle(), requestDto.getContent())
        ));
    }

    /*
     * 일정 삭제 (DELETE /schedules/{id})
     * @DeleteMapping: HTTP DELETE 요청을 이 메서드에 매핑
     * 삭제된 일정을 응답으로 반환
     * 로그인 필요 - 세션에서 userId를 꺼내 로그인 여부 확인, null이면 401 반환
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSchedules(@PathVariable Long id, HttpSession session) {
        /* 세션에서 userId를 꺼내 로그인 여부 확인 */
        Long userId = (Long) session.getAttribute("userId");

        /* 로그인 안 했으면 401 에러 반환 */
        if(userId == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }

        return ResponseEntity.ok(new ScheduleDeleteResponse(
                scheduleService.deleteSchedule(id)
        ));
    }
}
