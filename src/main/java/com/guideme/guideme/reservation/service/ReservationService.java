package com.guideme.guideme.reservation.service;

import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.domain.PostDetail;
import com.guideme.guideme.post.domain.Status;
import com.guideme.guideme.post.repository.PostDetailRepository;
import com.guideme.guideme.reservation.domain.Reservation;
import com.guideme.guideme.reservation.dto.ReservationDto;
import com.guideme.guideme.reservation.mapper.ReservationMapper;
import com.guideme.guideme.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PostDetailRepository postDetailRepository;

    public List<ReservationDto> getReservations(Long userId){
        return ReservationMapper.toReservationDtoList(reservationRepository.findByUserId(userId));
    }

    @Transactional
    public void reservation(ReservationDto reservationDto){
        PostDetail postDetail = postDetailRepository.findById(reservationDto.getPostDetailId())
                .orElseThrow(() -> new CustomException("예약이 불가능합니다."));

        if(postDetail.getStatus() == Status.CLOSED) throw new CustomException("예약이 불가능합니다.");
        Reservation reservation = ReservationMapper.toReservationEntity(reservationDto);
        postDetail.changeStatus(Status.CLOSED);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(Long userId, Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new CustomException("없는 예약내역입니다."));
        if(reservation.getUserId() != userId) throw new CustomException("회원정보가 일치하지 않습니다.");
        PostDetail postDetail = postDetailRepository.findById(reservation.getPostDetailId()).orElseThrow(() -> new CustomException("에러가 발생"));
        postDetail.changeStatus(Status.OPEN);
        reservationRepository.delete(reservation);
    }
}
