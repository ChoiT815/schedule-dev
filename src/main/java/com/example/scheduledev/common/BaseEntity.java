package com.example.scheduledev.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
 * 공통 시간 필드를 관리하는 기본 엔티티
 * User, Schedule 등 모든 엔티티에서 상속받아 사용
 * @MappedSuperclass: 이 클래스는 테이블로 생성되지 않고 상속받은 엔티티의 컬럼으로 포함됨
 * @EntityListeners: JPA Auditing 기능을 사용하기 위해 리스너 등록
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    /*========== 속성 ===========*/

    /*
     * @CreatedDate: 엔티티가 처음 저장될 때 현재 시간을 자동으로 넣어줌
     * @Column(updatable = false): 최초 생성 이후 이 컬럼은 UPDATE 시 제외 (변경 불가)
     */
    @CreatedDate
    @Column(updatable = false)
    /* 생성일 (자동 생성, 변경 불가) */
    private LocalDateTime createdAt;

    /*
     * @LastModifiedDate: 엔티티가 수정될 때마다 현재 시간을 자동으로 갱신
     */
    @LastModifiedDate
    /* 수정일 (수정할 때마다 자동 갱신) */
    private LocalDateTime updatedAt;

    /*========== Getter ===========*/

    /* 하위 엔티티에서 createdAt, updatedAt에 접근할 수 있도록 getter 제공 */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
