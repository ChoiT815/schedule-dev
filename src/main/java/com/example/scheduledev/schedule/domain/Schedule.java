package com.example.scheduledev.schedule.domain;

import com.example.scheduledev.common.BaseEntity;
import com.example.scheduledev.user.domain.User;
import jakarta.persistence.*;

/*
 * 일정 엔티티 클래스
 * @Entity: 이 클래스가 DB 테이블과 연결된 JPA 엔티티임을 선언
 * @Table: 연결할 DB 테이블 이름을 "schedules"로 지정
 * BaseEntity를 상속받아 createdAt, updatedAt 자동 관리
 */
@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity {
    /*========== 속성 ===========*/

    /*
     * @Id: 이 필드가 테이블의 기본키(PK)임을 선언
     * @GeneratedValue: PK 값을 DB가 자동으로 1씩 증가시켜 생성 (AUTO_INCREMENT)
     */
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 증가
     private Long id; /* 일정 고유 식별자 */
     private String title; /* 할일 제목 */
     private String content; /* 할일 내용 */

    /*
     * @ManyToOne: 여러 개의 Schedule이 하나의 User에 속하는 관계 (N:1)
     * @JoinColumn: DB에서 외래키 컬럼명을 "user_id"로 지정
     */
     @ManyToOne
     @JoinColumn(name = "user_id")
     private User user; /* 이 일정을 작성한 유저 (외래키로 연결) */

    /*========== 생성자 ===========*/

    /*
     * JPA는 기본 생성자가 반드시 필요함
     * protected로 선언해서 외부에서 직접 호출하지 못하게 막음
     */
    protected Schedule() {

    }

    /*
     * 일정 생성 시 사용하는 생성자
     * title: 제목, content: 내용, user: 작성자 (User 객체)
     */
    public Schedule(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    /*========== 기능 ===========*/

    /*
     * 일정 수정 메서드
     * @Transactional 환경에서 이 메서드 호출 시 더티체킹으로 자동 UPDATE 쿼리 실행
     * save()를 직접 호출하지 않아도 됨
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


    /*========== Getter ===========*/

    /* 하위 필드 접근을 위한 getter */
    public Long getId() {
        return id;
    }

    public String getTitle() {return title; }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

}
