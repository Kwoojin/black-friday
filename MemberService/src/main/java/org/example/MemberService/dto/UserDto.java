package org.example.MemberService.dto;

import lombok.Builder;
import org.example.MemberService.entity.UserEntity;

@Builder
public record UserDto(
    Long userId,
    String loginId,
    String userName
) {

    public static UserDto from(UserEntity entity) {
        return UserDto.builder()
            .userId(entity.getUserId())
            .loginId(entity.getLoginId())
            .userName(entity.getUserName())
            .build();
    }
}
