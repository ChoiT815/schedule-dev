package com.example.scheduledev.user.service;

import com.example.scheduledev.user.domain.User;
import com.example.scheduledev.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 생성(C)
    public User createUser(String username, String email, String password) {
        if(password.length() < 8) {
            throw new RuntimeException("비밀번호는 8글자 이상이어야 합니다.");
        }
        User user = new User(username, email, password);
        return userRepository.save(user);
    }

    // 전체 조회(R)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // 단건 조회(R)
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
    }

    // 수정(U)
    @Transactional
    public User updateUser(Long id, String username, String email ) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
        user.update(username, email);
        return user;
    }

    // 삭제(D)
    @Transactional
    public User deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 일정이 없습니다."));
        userRepository.delete(user);
        return user;
    }
}
