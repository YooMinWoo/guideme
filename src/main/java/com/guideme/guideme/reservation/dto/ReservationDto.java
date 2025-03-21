package com.guideme.guideme.reservation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationDto {

    private Long reservationId;

    private Long postId;
    private Long userId;

    private int price;      // 가격
    private int cnt;        // 인원 수

    private LocalDate start_date;
    private LocalDate end_date;
}
