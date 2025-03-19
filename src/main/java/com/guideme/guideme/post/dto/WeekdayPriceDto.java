package com.guideme.guideme.post.dto;

import com.guideme.guideme.post.domain.Weekday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeekdayPriceDto {

    private Long weekdayPriceId;
    private Long postId;
    
    private Weekday weekday;

    private int price;
}
