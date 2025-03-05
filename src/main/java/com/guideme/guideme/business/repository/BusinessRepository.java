package com.guideme.guideme.business.repository;

import com.guideme.guideme.business.domain.Business;
import com.guideme.guideme.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findByRegistrationNumber(String registrationNumber);
}
