package com.example.scheduledev.user.dto;

/* 유저 수정 시 클라이언트에서 받는 DTO (password 수정 불가) */
public class UserUpdateRequest {

    /*========== 속성 ===========*/

    /* 변경할 유저명 (닉네임) */
    private String username;

    /* 변경할 이메일 */
    private String email;

    /*========== Getter ===========*/

    /* JSON → 객체 변환 시 Jackson 라이브러리가 getter를 사용해서 값을 꺼냄 */
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
