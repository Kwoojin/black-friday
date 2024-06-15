package org.example.MemberService.service;

import lombok.RequiredArgsConstructor;
import org.example.MemberService.dto.UserDto;
import org.example.MemberService.entity.UserEntity;
import org.example.MemberService.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDto registerUser(String loginId, String userName) {
        UserEntity user = new UserEntity(loginId, userName);
        return UserDto.from(userRepository.save(user));
    }

    @Transactional
    public UserDto modifyUser(Long userId, String userName) {
        UserEntity user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        user.setUserName(userName);

        return UserDto.from(user);
    }

    public UserDto getUser(String loginId) {
        return UserDto.from(userRepository.findByLoginId(loginId).orElseThrow(IllegalArgumentException::new));
    }

}
