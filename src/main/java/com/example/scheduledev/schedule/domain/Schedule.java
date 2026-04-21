package com.example.scheduledev.schedule.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@EntityListeners(AuditingEntityListener.class)
public class Schedule {
     //========== 속성 ===========
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 증가
     private Long id;
     private String title;
     private String content;
     private String password;

     @CreatedDate // 만든 날짜
     @Column(updatable = false)
     private LocalDateTime createdAt;

     @LastModifiedDate // 수정 날짜
     private LocalDateTime updatedAt;
     private String author;

    //========== 생성자 ===========
    protected Schedule() {

    }

    public Schedule(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //========== Getter ===========
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
