package com.guideme.guideme.reservation.dto;

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

    public ReservationDto(Long userId, Long postDetailId, int people) {
        this.userId = userId;
        this.postDetailId = postDetailId;
        this.people = people;
    }
}
