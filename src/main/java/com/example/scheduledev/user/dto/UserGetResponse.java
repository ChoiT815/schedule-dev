package com.example.scheduledev.user.dto;

import com.example.scheduledev.user.domain.User;

import java.time.LocalDateTime;

/* 유저 조회 시 클라이언트에게 반환하는 DTO (password 제외) */
public class UserGetResponse {

    /*========== 속성 ===========*/

    /* 유저 고유 식별자 */
    private Long id;

    /* 유저명 (닉네임) */
    private String username;

    /* 이메일 */
    private String email;

    /* 유저 가입일 (자동 생성) */
    private LocalDateTime createdAt;

    /* 유저 정보 수정일 (자동 갱신) */
    private LocalDateTime updatedAt;

    /*========== 생성자 ===========*/

    /* User 엔티티를 받아서 필요한 필드만 꺼내 DTO에 담음, password는 보안상 제외 */
    public UserGetResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    /*========== Getter ===========*/

    /* 객체 → JSON 변환 시 Jackson 라이브러리가 getter를 사용해서 값을 꺼냄 */
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
