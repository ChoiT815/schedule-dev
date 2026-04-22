package com.example.scheduledev.schedule.dto;

/*
 * DTO(Data Transfer Object): 클라이언트 → 서버로 데이터를 전달할 때 사용하는 객체
 * 엔티티(Schedule)를 직접 노출하지 않고 필요한 데이터만 받기 위해 사용
 */
public class ScheduleRequestDto {

    /*========== 속성 ===========*/

    private Long userId; /* 일정을 작성할 유저의 ID (어떤 유저의 일정인지 식별) */
    private String title; /* 할일 제목 */
    private String content; /* 할일 내용 */

    /*========== Getter ===========*/

    /* JSON → 객체 변환 시 Jackson 라이브러리가 getter를 사용해서 값을 꺼냄 */
    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}




