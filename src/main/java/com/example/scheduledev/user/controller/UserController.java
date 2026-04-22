package com.example.scheduledev.user.controller;

import com.example.scheduledev.user.dto.UserRequestDto;
import com.example.scheduledev.user.dto.UserResponseDto;
import com.example.scheduledev.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 생성(C)
    @PostMapping
    public UserResponseDto createUsers(@RequestBody UserRequestDto requestDto) {
        return new UserResponseDto(
                userService.createUser(
                        requestDto.getUsername(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                )
        );
    }

    // 전체 조회(R)
    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers().stream()
                .map(UserResponseDto::new)
                .toList();
    }

    // 단건 조회(R)
    @GetMapping("/{id}")
    public UserResponseDto getUsers(@PathVariable Long id) {
        return new UserResponseDto(userService.getUser(id));
    }

    // 수정(U)
    @PatchMapping("/{id}")
    public UserResponseDto updateUsers(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return new UserResponseDto(
                userService.updateUser(id, requestDto.getUsername(), requestDto.getEmail())
        );
    }

    // 삭제(D)
    @DeleteMapping("/{id}")
    public UserResponseDto deleteUsers(@PathVariable Long id) {
        return new UserResponseDto(
                userService.deleteUser(id)
        );
    }
}
