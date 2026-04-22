package com.example.scheduledev.user.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/*
 * @Entity: 이 클래스가 DB 테이블과 연결된 JPA 엔티티임을 선언
 * @Table: 연결할 DB 테이블 이름을 "users"로 지정
 * @EntityListeners: JPA Auditing 기능을 사용하기 위해 리스너 등록 (createdAt, updatedAt 자동 관리)
 */
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    /*========== 속성 ===========*/

    /*
     * @Id: 이 필드가 테이블의 기본키(PK)임을 선언
     * @GeneratedValue: PK 값을 DB가 자동으로 1씩 증가시켜 생성 (AUTO_INCREMENT)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* 유저 고유 식별자 */
    private String username; /* 유저명 (닉네임) */
    private String email; /* 이메일 */

    private String password; /* 비밀번호 (8글자 이상) */

    /*
     * @CreatedDate: 엔티티가 처음 저장될 때 현재 시간을 자동으로 넣어줌
     * @Column(updatable = false): 최초 생성 이후 이 컬럼은 UPDATE 시 제외 (변경 불가)
     */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; /* 유저 가입일 (자동 생성, 변경 불가) */

    /*
     * @LastModifiedDate: 엔티티가 수정될 때마다 현재 시간을 자동으로 갱신
     */
    @LastModifiedDate
    private LocalDateTime updatedAt; /* 유저 정보 수정일 (수정할 때마다 자동 갱신) */

    /*========== 생성자 ===========*/

    /*
     * JPA는 기본 생성자가 반드시 필요함
     * protected로 선언해서 외부에서 직접 호출하지 못하게 막음
     */
    protected User() {

    }

    /*
     * 유저 생성 시 사용하는 생성자
     * username: 유저명, email: 이메일, password: 비밀번호
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /*========== 기능 ===========*/

    /*
     * 유저 정보 수정 메서드 (username, email만 수정 가능, password는 별도 처리)
     * @Transactional 환경에서 이 메서드 호출 시 더티체킹으로 자동 UPDATE 쿼리 실행
     */
    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }

    /*========== Getter ===========*/
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
