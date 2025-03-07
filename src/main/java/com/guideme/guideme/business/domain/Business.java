package com.guideme.guideme.business.domain;

import com.guideme.guideme.business.dto.BusinessDto;
import com.guideme.guideme.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Business extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "business_id")
    private Long id;

    private Long userId;

    private String tradeName;  // 상호명
    private String registrationNumber; // 사업자 등록번호

    @Builder
    public Business(Long userId, String tradeName, String registrationNumber) {
        this.userId = userId;
        this.tradeName = tradeName;
        this.registrationNumber = registrationNumber;
    }

    public static Business createBusiness(BusinessDto businessDto){
        return Business.builder()
                .userId(businessDto.getUserId())
                .tradeName(businessDto.getTradeName())
                .registrationNumber(businessDto.getRegistrationNumber())
                .build();
    }
}
