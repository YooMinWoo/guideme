package com.guideme.guideme.post.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeasonalPrice {

    @Id
    @GeneratedValue
    @Column(name = "seasonal_price_id")
    private Long id;

    private Long post_id;
    private LocalDateTime date;

    private int price;

    @Builder
    public SeasonalPrice(Long post_id, LocalDateTime date, int price) {
        this.post_id = post_id;
        this.date = date;
        this.price = price;
    }
}
