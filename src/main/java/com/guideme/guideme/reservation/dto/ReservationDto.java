package com.guideme.guideme.reservation.dto;

import com.guideme.guideme.reservation.domain.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private Long reservationId;

    private Long userId;
    private Long postDetailId;

    private int people;

    private int payment;

    private ReservationStatus reservationStatus;

    public ReservationDto(Long userId, Long postDetailId, int people, int payment, ReservationStatus reservationStatus) {
        this.userId = userId;
        this.postDetailId = postDetailId;
        this.people = people;
        this.payment = payment;
        this.reservationStatus = reservationStatus;
    }
}
