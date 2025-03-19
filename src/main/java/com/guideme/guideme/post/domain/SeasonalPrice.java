package com.guideme.guideme.post.domain;

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
public class SeasonalPrice {

    @Id
    @GeneratedValue
    @Column(name = "seasonal_price_id")
    private Long id;

    private Long postId;
    private LocalDate date;

    private int price;

    @Builder
    public SeasonalPrice(Long postId, LocalDate date, int price) {
        this.postId = postId;
        this.date = date;
        this.price = price;
    }
}
