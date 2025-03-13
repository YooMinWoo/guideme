package com.guideme.guideme.reservation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationDto {

    private Long reservation_id;

    private Long post_id;
    private Long user_id;

    private int price;      // 가격
    private int cnt;        // 인원 수

    private int totalPrice; // 총 가격
}
