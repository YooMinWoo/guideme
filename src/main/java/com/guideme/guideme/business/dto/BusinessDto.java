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

    private Long id;
    private Long userId;
    private String tradeName;  // 상호명
    private String registrationNumber; // 사업자 등록번호

}
