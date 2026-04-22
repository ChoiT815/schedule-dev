package com.example.scheduledev.user.dto;

/* 로그인 요청 시 클라이언트에서 받는 DTO */
public class LoginRequest {
    /*========== 속성 ===========*/

    /* 로그인에 사용할 이메일 */
    private String email;

    /* 로그인에 사용할 비밀번호 */
    private String password;

    /*========== Getter ===========*/

    /* JSON → 객체 변환 시 Jackson 라이브러리가 getter를 사용해서 값을 꺼냄 */
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
