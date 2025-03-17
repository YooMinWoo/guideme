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

    private Long post_id;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;    // 요일


    private int price;

    @Builder
    public WeekdayPrice(Long post_id, Weekday weekday, int price) {
        this.post_id = post_id;
        this.weekday = weekday;
        this.price = price;
    }
}
