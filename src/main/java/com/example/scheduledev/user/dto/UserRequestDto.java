package com.example.scheduledev.user.dto;

/*
 * DTO(Data Transfer Object): 클라이언트 → 서버로 데이터를 전달할 때 사용하는 객체
 * 엔티티(User)를 직접 노출하지 않고 필요한 데이터만 받기 위해 사용
 */
public class UserRequestDto {

    /*========== 속성 ===========*/

    private String username; /* 유저명 (닉네임) */
    private String email; /* 이메일 */
    private String password; /* 비밀번호 (8글자 이상 검증은 Service에서 처리) */

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
