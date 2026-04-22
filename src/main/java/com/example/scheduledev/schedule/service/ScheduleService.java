package com.example.scheduledev.schedule.service;

import com.example.scheduledev.schedule.domain.Schedule;
import com.example.scheduledev.schedule.repository.ScheduleRepository;
import com.example.scheduledev.user.domain.User;
import com.example.scheduledev.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * 일정 관련 비즈니스 로직을 담당하는 Service 클래스
 * @Service: Spring이 자동으로 Bean으로 등록해서 Controller에서 주입받아 사용 가능
 */
@Service
public class ScheduleService {

    /*========== 속성 ===========*/

    /* final: 한 번 주입받으면 변경 불가 (불변성 보장) */
    private final ScheduleRepository scheduleRepository; /* 일정 DB 접근 담당 */
    private final UserRepository userRepository; /* 유저 DB 접근 담당 (일정 생성 시 유저 조회용) */

    /*========== 생성자 ===========*/

    /*
     * 생성자 주입 방식: @Autowired 없이 Spring이 자동으로 의존성 주입
     * 생성자가 하나면 @Autowired 생략 가능
     */
    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    /*========== 기능 ===========*/

    /*
     * 일정 생성
     * 세션에서 받은 userId로 User를 먼저 조회한 뒤, 새 Schedule을 생성해서 DB에 저장
     */
    public Schedule createSchedule(String title, String content, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저가 없습니다."));
        Schedule schedule = new Schedule(title, content, user);
        return scheduleRepository.save(schedule); /* INSERT 쿼리 실행 */
    }

    /*
     * 일정 전체 조회
     * DB의 모든 일정을 리스트로 반환 (SELECT * FROM schedules)
     */
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    /*
     * 일정 단건 조회
     * id로 일정 하나를 조회, 없으면 예외 발생
     */
    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
    }

    /*
     * 일정 수정
     * @Transactional: 트랜잭션 범위 안에서 엔티티 변경을 감지(더티체킹)해서 자동으로 UPDATE 쿼리 실행
     * save()를 직접 호출하지 않아도 됨
     */
    @Transactional
    public Schedule updateSchedule(Long id, String title, String content) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(   () -> new RuntimeException("해당 일정이 없습니다."));
        schedule.update(title, content); /* 값 변경 → 트랜잭션 종료 시 자동 UPDATE */
        return schedule;
    }

    /*
     * 일정 삭제
     * @Transactional: 삭제 작업도 트랜잭션 안에서 처리
     * 삭제된 일정을 반환해서 Controller에서 응답으로 사용
     */
    @Transactional
    public Schedule deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
        scheduleRepository.delete(schedule); /* DELETE 쿼리 실행 */
        return schedule;
    }
}
