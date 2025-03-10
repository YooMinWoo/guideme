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
    private String businessMobile;  // 대표 전화번호
    private String businessEmail;  // 대표 이메일
    private String registrationNumber; // 사업자 등록번호
    private String information;  // 상세정보(소개글 등)

    @Builder
    public Business(Long userId, String tradeName, String businessMobile, String businessEmail, String registrationNumber, String information) {
        this.userId = userId;
        this.tradeName = tradeName;
        this.businessMobile = businessMobile;
        this.businessEmail = businessEmail;
        this.registrationNumber = registrationNumber;
        this.information = information;
    }
}
