package com.guideme.guideme.user.service;

import com.guideme.guideme.user.domain.Role;
import com.guideme.guideme.user.dto.UserDto;
import com.guideme.guideme.user.domain.User;
import com.guideme.guideme.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signup(UserDto userDto) throws Exception {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) throw new Exception("이미 존재하는 아이디입니다.");
        User user = User.builder()
                    .username(userDto.getUsername())
                    .password(userDto.getPassword())
                    .email(userDto.getEmail())
                    .role(Role.ROLE_USER)
                    .build();

        userRepository.save(user);
    }
}
