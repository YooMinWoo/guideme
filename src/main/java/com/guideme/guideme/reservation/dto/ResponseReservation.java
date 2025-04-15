package com.guideme.guideme.reservation.dto;

import com.guideme.guideme.reservation.domain.ReservationStatus;
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
public class ResponseReservation {

    private String title;   // 제목
    private String description; // 설명

    private LocalDate startDate;        // 일자
    private int pricePerTeam;   // 날짜별 한 팀 당 가격

    private int people;     // 예약 인원
    private int payment;    // 지불 금액

    private ReservationStatus reservationStatus;

    private LocalDateTime createDate;   // 예약 일자
}
