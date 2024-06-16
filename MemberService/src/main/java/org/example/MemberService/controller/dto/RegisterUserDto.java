package org.example.MemberService.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegisterUserDto {
    private String loginId;
    private String userName;
}
