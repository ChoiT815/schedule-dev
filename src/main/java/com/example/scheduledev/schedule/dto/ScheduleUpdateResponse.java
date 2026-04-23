package com.example.scheduledev.schedule.dto;

import com.example.scheduledev.schedule.domain.Schedule;

import java.time.LocalDateTime;

/* 일정 수정 성공 시 클라이언트에게 반환하는 DTO */
public class ScheduleUpdateResponse {

    /*========== 속성 ===========*/

    /* 일정 고유 식별자 */
    private Long id;

    /* 작성자 유저 ID (User 객체 대신 ID만 노출) */
    private Long userId;

    /* 변경된 할일 제목 */
    private String title;

    /* 변경된 할일 내용 */
    private String content;

    /* 일정 작성일 (자동 생성) */
    private LocalDateTime createdAt;

    /* 일정 수정일 (자동 갱신) */
    private LocalDateTime updatedAt;

    /*========== 생성자 ===========*/

    /* Schedule 엔티티를 받아서 필요한 필드만 꺼내 DTO에 담음 */
    public ScheduleUpdateResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.userId = schedule.getUser().getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

    /*========== Getter ===========*/

    /* 객체 → JSON 변환 시 Jackson 라이브러리가 getter를 사용해서 값을 꺼냄 */
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
