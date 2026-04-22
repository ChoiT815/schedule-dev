package com.example.scheduledev.schedule.domain;

import com.example.scheduledev.user.domain.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
 * @Entity: 이 클래스가 DB 테이블과 연결된 JPA 엔티티임을 선언
 * @Table: 연결할 DB 테이블 이름을 "schedules"로 지정
 * @EntityListeners: JPA Auditing 기능을 사용하기 위해 리스너 등록 (createdAt, updatedAt 자동 관리)
 */

@Entity
@Table(name = "schedules")
@EntityListeners(AuditingEntityListener.class)
public class Schedule {
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
     * @CreatedDate: 엔티티가 처음 저장될 때 현재 시간을 자동으로 넣어줌
     * @Column(updatable = false): 최초 생성 이후 이 컬럼은 UPDATE 시 제외 (변경 불가)
     */
     @CreatedDate
     @Column(updatable = false)
     private LocalDateTime createdAt; /* 일정 작성일 (자동 생성, 변경 불가) */

    /*
     * @LastModifiedDate: 엔티티가 수정될 때마다 현재 시간을 자동으로 갱신
     */
     @LastModifiedDate /* 일정 수정일 (수정할 때마다 자동 갱신) */
     private LocalDateTime updatedAt;

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
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


    /*========== Getter ===========*/
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
