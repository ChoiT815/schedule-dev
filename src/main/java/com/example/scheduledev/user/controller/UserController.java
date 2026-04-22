package com.example.scheduledev.user.controller;

import com.example.scheduledev.user.domain.User;
import com.example.scheduledev.user.dto.*;
import com.example.scheduledev.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @RestController: @Controller + @ResponseBody 합친 것
 * 모든 메서드의 반환값을 JSON으로 변환해서 응답
 * @RequestMapping: 이 Controller의 기본 URL을 "/users"로 지정
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /*========== 속성 ===========*/

    /* final: 한 번 주입받으면 변경 불가 (불변성 보장) */
    private final UserService userService; /* 유저 비즈니스 로직 담당 */

    /*========== 생성자 ===========*/

    /*
     * 생성자 주입 방식: @Autowired 없이 Spring이 자동으로 의존성 주입
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*========== 기능 ===========*/

    @PostMapping("/login")
    public UserCreateResponse login(@RequestBody LoginRequestDto requestDto, HttpSession session) {
        User user = userService.login(requestDto.getEmail(), requestDto.getPassword());

        session.setAttribute("userId", user.getId());
        return new UserCreateResponse(user);
    }

    /*
     * 유저 생성 (POST /users)
     * @PostMapping: HTTP POST 요청을 이 메서드에 매핑
     * @RequestBody: HTTP 요청 Body의 JSON을 UserRequestDto 객체로 변환
     */
    @PostMapping
    public UserCreateResponse createUsers(@RequestBody UserCreateRequest requestDto) {
        return new UserCreateResponse (
                userService.createUser(
                        requestDto.getUsername(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                )
        );
    }

    /*
     * 전체 조회 (GET /users)
     * @GetMapping: HTTP GET 요청을 이 메서드에 매핑
     * 모든 유저를 List<UserResponseDto>로 반환
     */
    @GetMapping
    public List<UserGetResponse> getUsers() {
        return userService.getUsers().stream()
                .map(UserGetResponse::new) /* User → UserResponseDto 변환 */
                .toList();
    }

    /*
     * 단건 조회 (GET /users/{id})
     * @PathVariable: URL 경로의 {id} 값을 Long id 파라미터로 받음
     */
    @GetMapping("/{id}")
    public UserGetResponse getUsers(@PathVariable Long id) {
        return new UserGetResponse(userService.getUser(id));
    }

    /*
     * 수정 (PATCH /users/{id})
     * @PatchMapping: HTTP PATCH 요청을 이 메서드에 매핑 (일부 수정)
     * username, email만 수정 가능 (password는 별도 처리)
     */
    @PatchMapping("/{id}")
    public UserUpdateResponse updateUsers(@PathVariable Long id, @RequestBody UserUpdateRequest requestDto) {
        return new UserUpdateResponse(
                userService.updateUser(id, requestDto.getUsername(), requestDto.getEmail())
        );
    }

    /*
     * 삭제 (DELETE /users/{id})
     * @DeleteMapping: HTTP DELETE 요청을 이 메서드에 매핑
     * 삭제된 유저를 응답으로 반환
     */
    @DeleteMapping("/{id}")
    public UserDeleteResponse deleteUsers(@PathVariable Long id) {
        return new UserDeleteResponse(
                userService.deleteUser(id)
        );
    }
}
