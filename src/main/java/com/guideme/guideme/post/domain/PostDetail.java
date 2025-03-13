package com.guideme.guideme.post.domain;

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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetail extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_detail_id")
    private Long id;

    private Long user_id;   // 가이드
    private Long post_id;

    private int price;  // 요금
    private LocalDate postDate; // 날짜

    public void changePrice(int price){
        this.price = price;
    }




//    private int adultPrice;  // 대인 요금
//    private int childPrice;  // 소인 요금

//    private int max;    // 최대 인원
//    private int min;    // 최소 인원


    @Builder
    public PostDetail(Long user_id, Long post_id, int price, LocalDate postDate) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.price = price;
        this.postDate = postDate;
    }
}
