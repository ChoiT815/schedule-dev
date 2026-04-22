package com.example.scheduledev.user.domain;

import com.example.scheduledev.common.BaseEntity;
import jakarta.persistence.*;

/*
 * @Entity: 이 클래스가 DB 테이블과 연결된 JPA 엔티티임을 선언
 * @Table: 연결할 DB 테이블 이름을 "users"로 지정
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {
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

    public String getPassword() { return password; }

}
