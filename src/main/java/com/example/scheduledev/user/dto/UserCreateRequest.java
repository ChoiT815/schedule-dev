package com.example.scheduledev.user.dto;

/* 유저 생성 시 클라이언트에서 받는 DTO */
public class UserCreateRequest {

    /*========== 속성 ===========*/

    /* 유저명 (닉네임) */
    private String username;

    /* 이메일 */
    private String email;

    /* 비밀번호 (8글자 이상, Service에서 검증) */
    private String password;

    /*========== Getter ===========*/

    /* JSON → 객체 변환 시 Jackson 라이브러리가 getter를 사용해서 값을 꺼냄 */
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
