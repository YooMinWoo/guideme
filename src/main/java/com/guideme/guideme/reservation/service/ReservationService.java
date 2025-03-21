package com.guideme.guideme.reservation.service;

import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.post.domain.Status;
import com.guideme.guideme.post.service.PostService;
import com.guideme.guideme.reservation.domain.Reservation;
import com.guideme.guideme.reservation.dto.ReservationDto;
import com.guideme.guideme.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Status postStatus(Long postId, LocalDate startDate, LocalDate endDate){
        Optional<Reservation> reservation = reservationRepository.findReservationStatus(postId, startDate, endDate);
        if(reservation.isEmpty()) return Status.OPEN;
        return Status.CLOSED;
    }

    public ReservationDto reservation(ReservationDto request) {
        Optional<Reservation> reservation = reservationRepository.findReservationStatus(
                request.getPostId(), request.getStart_date(), request.getEnd_date());
        if(reservation.isPresent()) throw new CustomException("이미 누군가 예약을 먼저했네 ㅠㅠ");
        // 날짜별 가격 입력하는거 만들어야 함
        return request;
    }
}
