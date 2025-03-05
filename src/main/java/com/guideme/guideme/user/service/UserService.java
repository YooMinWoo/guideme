package com.guideme.guideme.user.service;

import com.guideme.guideme.business.domain.Business;
import com.guideme.guideme.business.dto.BusinessDto;
import com.guideme.guideme.business.service.BusinessService;
import com.guideme.guideme.user.domain.Role;
import com.guideme.guideme.user.dto.SignupDto;
import com.guideme.guideme.user.dto.UserDto;
import com.guideme.guideme.user.domain.User;
import com.guideme.guideme.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BusinessService businessService;

    public void userSignup(UserDto userDto) throws Exception {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) throw new Exception("이미 존재하는 아이디입니다.");
        User user = getUserEntity(userDto);

        userRepository.save(user);
    }

    @Transactional
    public void guideSignup(UserDto userDto, BusinessDto businessDto) throws Exception {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) throw new Exception("이미 존재하는 아이디입니다.");
        if(businessService.findByRegistration_number(businessDto.getRegistrationNumber()).isPresent()) throw new Exception("이미 존재하는 사업자 등록번호입니다.");
        User user = getUserEntity(userDto);
        userRepository.save(user);

        businessDto.setUserId(user.getId());
        Business business = getBusinessEntity(businessDto);
        businessService.save(business);
    }

    public User getUserEntity(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .mobile(userDto.getMobile())
                .role(userDto.getRole())
                .build();
    }

    public Business getBusinessEntity(BusinessDto businessDto){
        return Business.builder()
                .userId(businessDto.getUserId())
                .tradeName(businessDto.getTradeName())
                .registrationNumber(businessDto.getRegistrationNumber())
                .build();
    }
}
