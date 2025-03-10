package com.guideme.guideme.post.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.guideme.guideme.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private Long user_id;

    private String title;   // 제목
    private String description; // 설명
    private int adultPrice;  // 대인 요금
    private int childPrice;  // 소인 요금
    private LocalDate postDate; // 날짜
    private ReservationStatus reservationStatus;

    @Builder
    public Post(Long user_id, String title, String description, int adultPrice, int childPrice, LocalDate postDate, ReservationStatus reservationStatus) {
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.postDate = postDate;
        this.reservationStatus = reservationStatus;
    }
}
