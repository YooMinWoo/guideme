package com.guideme.guideme.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonalPriceDto {

    private Long seasonal_price_id;
    private Long post_id;

    private LocalDateTime date;

    private int price;
}
