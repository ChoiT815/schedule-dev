package com.example.scheduledev.schedule.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * 전역 예외 처리 핸들러
 * @RestControllerAdvice: 모든 Controller에서 발생하는 예외를 한 곳에서 처리
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * 잘못된 요청 처리 (400)
     * ex) 존재하지 않는 일정 조회/수정/삭제 시
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    /*
     * 서버 내부 오류 처리 (500)
     * 예상치 못한 예외 발생 시 일괄 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.status(500).body("서버 오류가 발생했습니다.");
    }
}