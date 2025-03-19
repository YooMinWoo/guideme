package com.guideme.guideme.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonalPriceDto {

    private Long seasonalPriceId;
    private Long postId;

    private LocalDate date;

    private int price;
}
