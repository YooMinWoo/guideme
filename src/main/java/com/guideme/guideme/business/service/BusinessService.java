package com.guideme.guideme.business.service;

import com.guideme.guideme.business.domain.Business;
import com.guideme.guideme.business.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;

    public Optional<Business> findByRegistration_number(String registrationNumber){
        return businessRepository.findByRegistrationNumber(registrationNumber);
    }

    public Business save(Business business) {
        return businessRepository.save(business);
    }
}
