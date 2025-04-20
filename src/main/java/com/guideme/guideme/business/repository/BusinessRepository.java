package com.guideme.guideme.business.repository;

import com.guideme.guideme.business.domain.Business;
import com.guideme.guideme.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findByRegistrationNumber(String registrationNumber);

    Optional<Business> findByUserId(Long userId);
}
