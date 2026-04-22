package com.example.scheduledev.user.service;

import com.example.scheduledev.user.domain.User;
import com.example.scheduledev.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * @Service: 이 클래스가 비즈니스 로직을 담당하는 서비스 계층임을 선언
 * Spring이 자동으로 Bean으로 등록해서 Controller에서 주입받아 사용 가능
 */
@Service
public class UserService {

    /*========== 속성 ===========*/

    /* final: 한 번 주입받으면 변경 불가 (불변성 보장) */
    private final UserRepository userRepository; /* 유저 DB 접근 담당 */

    /*========== 생성자 ===========*/

    /*
     * 생성자 주입 방식: @Autowired 없이 Spring이 자동으로 의존성 주입
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*========== 기능 ===========*/


    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당 유저가 없습니다."));

        if(!user.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    /*
     * 유저 생성
     * 비밀번호 8글자 미만이면 예외 발생, 통과하면 DB에 저장
     */
    public User createUser(String username, String email, String password) {
        /* 비밀번호 길이 검증 (8글자 미만이면 예외 발생) */
        if(password.length() < 8) {
            throw new RuntimeException("비밀번호는 8글자 이상이어야 합니다.");
        }
        User user = new User(username, email, password);
        return userRepository.save(user); /* INSERT 쿼리 실행 */
    }

    /*
     * 전체 조회
     * DB의 모든 유저를 리스트로 반환 (SELECT * FROM users)
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /*
     * 단건 조회
     * id로 유저 하나를 조회, 없으면 예외 발생
     */
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
    }

    /*
     * 수정
     * @Transactional: 트랜잭션 범위 안에서 엔티티 변경을 감지(더티체킹)해서 자동으로 UPDATE 쿼리 실행
     * save()를 직접 호출하지 않아도 됨
     */
    @Transactional
    public User updateUser(Long id, String username, String email ) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
        user.update(username, email); /* 값 변경 → 트랜잭션 종료 시 자동 UPDATE */
        return user;
    }

    /*
     * 삭제
     * @Transactional: 삭제 작업도 트랜잭션 안에서 처리
     * 삭제된 유저를 반환해서 Controller에서 응답으로 사용
     */
    @Transactional
    public User deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
        userRepository.delete(user); /* DELETE 쿼리 실행 */
        return user;
    }
}
