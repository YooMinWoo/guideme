package com.guideme.guideme.reservation.mapper;

import com.guideme.guideme.reservation.domain.Reservation;
import com.guideme.guideme.reservation.dto.ReservationDto;

import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {

    public static Reservation toReservationEntity(ReservationDto reservationDto){
        return Reservation.builder()
                .userId(reservationDto.getUserId())
                .postDetailId(reservationDto.getPostDetailId())
                .people(reservationDto.getPeople())
                .payment(reservationDto.getPayment())
                .reservationStatus(reservationDto.getReservationStatus())
                .build();
    }

    public static ReservationDto toReservationDto(Reservation reservation){
        return ReservationDto.builder()
                .reservationId(reservation.getId())
                .postDetailId(reservation.getPostDetailId())
                .userId(reservation.getUserId())
                .people(reservation.getPeople())
                .payment(reservation.getPayment())
                .reservationStatus(reservation.getReservationStatus())
                .build();
    }

    public static List<ReservationDto> toReservationDtoList(List<Reservation> reservationList){
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for(Reservation reservation : reservationList){
            ReservationDto reservationDto = ReservationDto.builder()
                                        .reservationId(reservation.getId())
                                        .postDetailId(reservation.getPostDetailId())
                                        .userId(reservation.getUserId())
                                        .people(reservation.getPeople())
                                        .build();

            reservationDtoList.add(reservationDto);
        }
        return reservationDtoList;
    }
}
