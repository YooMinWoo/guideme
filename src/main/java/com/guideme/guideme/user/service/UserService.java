package com.guideme.guideme.user.service;

import com.guideme.guideme.business.domain.Business;
import com.guideme.guideme.business.dto.BusinessDto;
import com.guideme.guideme.business.service.BusinessService;
import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.global.exception.UserNotFoundException;
import com.guideme.guideme.global.exception.UserNotRegisteredException;
import com.guideme.guideme.security.jwt.JwtUtil;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.dto.TokenDto;
import com.guideme.guideme.user.dto.UserDto;
import com.guideme.guideme.user.domain.User;
import com.guideme.guideme.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BusinessService businessService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void userSignup(UserDto userDto) {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) throw new CustomException("이미 존재하는 아이디입니다.");
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = User.createUser(userDto);

        userRepository.save(user);
    }

    @Transactional
    public void guideSignup(UserDto userDto, BusinessDto businessDto) {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) throw new CustomException("이미 존재하는 아이디입니다.");
        if(businessService.findByRegistration_number(businessDto.getRegistrationNumber()).isPresent()) throw new CustomException("이미 존재하는 사업자 등록번호입니다.");
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = User.createUser(userDto);
        userRepository.save(user);

        businessDto.setUserId(user.getId());
        Business business = Business.createBusiness(businessDto);
        businessService.save(business);
    }

    public TokenDto login(UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();

        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new CustomException("존재하지 않는 아이디입니다.");
        if(!passwordEncoder.matches(password, user.get().getPassword())) throw new CustomException("비밀번호가 일치하지 않습니다.");

        return jwtUtil.generateToken(user.get());
    }
}
