package com.guideme.guideme.business.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class BusinessDto {

    private Long business_id;
    private Long userId;
    private String tradeName;  // 상호명
    private String businessMobile;  // 대표 전화번호
    private String businessEmail;  // 대표 이메일
    private String registrationNumber; // 사업자 등록번호
    private String information; // 상세정보(소개글 등)

}
