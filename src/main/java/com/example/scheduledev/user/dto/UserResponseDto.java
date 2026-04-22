package com.example.scheduledev.user.dto;

import com.example.scheduledev.user.domain.User;

import java.time.LocalDateTime;

/*
 * DTO(Data Transfer Object): 서버 → 클라이언트로 데이터를 전달할 때 사용하는 객체
 * 엔티티(User)를 직접 노출하지 않고 필요한 데이터만 응답으로 보내기 위해 사용
 * password 같은 민감한 정보를 숨기고 원하는 필드만 선택적으로 응답 가능
 */
public class UserResponseDto {

    /*========== 속성 ===========*/

    private Long id; /* 유저 고유 식별자 */
    private String username; /* 유저명 (닉네임) */
    private String email; /* 이메일 */
    /* password는 보안상 응답에 포함하지 않음! */
    private LocalDateTime createdAt; /* 유저 가입일 */
    private LocalDateTime updatedAt; /* 유저 정보 수정일 */

    /*========== 생성자 ===========*/

    /*
     * User 엔티티를 받아서 필요한 필드만 꺼내 DTO에 담음
     * password는 의도적으로 제외 (보안)
     */
    public UserResponseDto(User user) {
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
