package org.example.MemberService.controller;

import lombok.RequiredArgsConstructor;
import org.example.MemberService.controller.dto.ModifyUserDto;
import org.example.MemberService.controller.dto.RegisterUserDto;
import org.example.MemberService.dto.UserDto;
import org.example.MemberService.service.UserService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/member/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public UserDto registerUser(@RequestBody RegisterUserDto dto) {
        return userService.registerUser(dto.getLoginId(), dto.getUserName());
    }

    @PutMapping("/{userId}/modify")
    public UserDto modifyUser(@PathVariable Long userId, @RequestBody ModifyUserDto dto) {
        return userService.modifyUser(userId, dto.getUserName());
    }

    @PostMapping("/{loginId}/login")
    public UserDto login(@PathVariable String loginId) {
        return userService.getUser(loginId);
    }
}
