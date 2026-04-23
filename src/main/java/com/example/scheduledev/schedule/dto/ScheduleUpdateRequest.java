package com.example.scheduledev.schedule.dto;

/* 일정 수정 시 클라이언트에서 받는 DTO */
public class ScheduleUpdateRequest {

    /*========== 속성 ===========*/

    /* 변경할 할일 제목 */
    private String title;

    /* 변경할 할일 내용 */
    private String content;

    /*========== Getter ===========*/

    /* JSON → 객체 변환 시 Jackson 라이브러리가 getter를 사용해서 값을 꺼냄 */
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
