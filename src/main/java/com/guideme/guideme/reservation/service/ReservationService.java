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
import java.time.temporal.ChronoUnit;
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
        /*
        4월 10일부터 4월 13일이라고 봤을 때
        예약을 할 때

        예약 가능한 일자인지 확인
        해당하는 날짜의 가격 가져오기

        sesonal, weekend 빼고 일일이 기입
         */

        long until = request.getStart_date().until(request.getEnd_date(), ChronoUnit.DAYS);
        for(int i = 0; i <= until; i++){

        }
        return request;
    }
}
