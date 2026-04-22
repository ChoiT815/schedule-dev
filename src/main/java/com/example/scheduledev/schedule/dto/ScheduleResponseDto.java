package com.example.scheduledev.schedule.dto;

import com.example.scheduledev.schedule.domain.Schedule;

import java.time.LocalDateTime;

/*
 * DTO(Data Transfer Object): 서버 → 클라이언트로 데이터를 전달할 때 사용하는 객체
 * 엔티티(Schedule)를 직접 노출하지 않고 필요한 데이터만 응답으로 보내기 위해 사용
 * password 같은 민감한 정보를 숨기고 원하는 필드만 선택적으로 응답 가능
 */
public class ScheduleResponseDto {

    /*========== 속성 ===========*/

    private Long id; /* 일정 고유 식별자 */
    private String title; /* 할일 제목 */
    private String content; /* 할일 내용 */
    private Long userId; /* 작성자 유저 ID (User 객체 대신 ID만 노출) */
    private LocalDateTime createdAt; /* 일정 작성일 */
    private LocalDateTime updatedAt; /* 일정 수정일 */

    /*========== 생성자 ===========*/

    /*
     * Schedule 엔티티를 받아서 필요한 필드만 꺼내 DTO에 담음
     * schedule.getUser().getId(): User 객체에서 id만 꺼내서 userId에 저장
     */
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.userId = schedule.getUser().getId(); /* User 전체가 아닌 id만 노출 */
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

    /*========== Getter ===========*/

    /* 객체 → JSON 변환 시 Jackson 라이브러리가 getter를 사용해서 값을 꺼냄 */
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
