package com.guideme.guideme.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeekdayPrice {

    @Id
    @GeneratedValue
    @Column(name = "weekday_price_id")
    private Long id;

    private Long postId;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;    // 요일


    private int price;

    @Builder
    public WeekdayPrice(Long postId, Weekday weekday, int price) {
        this.postId = postId;
        this.weekday = weekday;
        this.price = price;
    }
}
