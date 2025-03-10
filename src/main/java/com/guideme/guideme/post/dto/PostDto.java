package com.guideme.guideme.post.dto;

import com.guideme.guideme.post.domain.ReservationStatus;
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
public class PostDto {

    private Long post_id;

    private Long user_id;

    private String title;   // 제목
    private String description; // 설명
    private int adultPrice;  // 대인 요금
    private int childPrice;  // 소인 요금
    private LocalDate postDate; // 날짜
    private ReservationStatus reservationStatus; // 상태
}
