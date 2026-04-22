package com.example.scheduledev.schedule.dto;

/* 일정 생성 시 클라이언트에서 받는 DTO (userId는 세션에서 가져오므로 불필요) */
public class ScheduleCreateRequest {

    /*========== 속성 ===========*/

    /* 할일 제목 */
    private String title;

    /* 할일 내용 */
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
