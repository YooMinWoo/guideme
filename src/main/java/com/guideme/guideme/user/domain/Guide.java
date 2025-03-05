package com.guideme.guideme.user.domain;

import com.guideme.guideme.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Guide extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "guide_id")
    private Long id;

    private String username;
    private String password;

    private String email;   // 대표 이메일
    private String mobile;  // 대표 연락처
    private String name;    // 대표자명
    private String registration_number; // 사업자 등록번호
    private String trade_name;  // 상호명


}
