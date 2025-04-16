package com.guideme.guideme.reservation.service;

import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.domain.PostDetail;
import com.guideme.guideme.post.domain.Status;
import com.guideme.guideme.post.repository.PostDetailRepository;
import com.guideme.guideme.post.repository.PostRepository;
import com.guideme.guideme.reservation.domain.Reservation;
import com.guideme.guideme.reservation.domain.ReservationStatus;
import com.guideme.guideme.reservation.dto.ReservationDto;
import com.guideme.guideme.reservation.dto.ResponseReservation;
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
    private final PostRepository postRepository;

    public List<ResponseReservation> getReservations(Long userId){
        List<ResponseReservation> responseReservationList = new ArrayList<>();
        List<Reservation> reservationList = reservationRepository.findByUserId(userId);

        for(Reservation reservation : reservationList){
            PostDetail postDetail = postDetailRepository.findById(reservation.getPostDetailId()).orElseThrow(() -> new CustomException("예약 내역을 찾을 수 없습니다."));
            Post post = postRepository.findById(postDetail.getPostId()).orElseThrow(() -> new CustomException("예약 내역을 찾을 수 없습니다."));

            ResponseReservation result = ResponseReservation.builder()
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .startDate(postDetail.getStartDate())
                    .pricePerTeam(postDetail.getPricePerTeam())
                    .people(reservation.getPeople())
                    .payment(reservation.getPayment())
                    .createDate(reservation.getCreatedDate())
                    .reservationStatus(reservation.getReservationStatus())
                    .build();

            responseReservationList.add(result);
        }
        return responseReservationList;
    }

    // 예약하기
    @Transactional
    public void reservation(ReservationDto reservationDto){
        PostDetail postDetail = postDetailRepository.findById(reservationDto.getPostDetailId())
                .orElseThrow(() -> new CustomException("공습경보!!!"));
        Post post = postRepository.findById(postDetail.getPostId())
                .orElseThrow(() -> new CustomException("경찰력 총동원!!!"));

        if(postDetail.getStatus() == Status.CLOSED || post.getStatus() == Status.CLOSED || postDetail.getAvailableCnt() == 0) throw new CustomException("예약이 불가능합니다.");
        if(reservationDto.getPeople() < post.getMaxPeople() || reservationDto.getPeople() > post.getMaxPeople()) throw new CustomException("해당되지 않는 인원수입니다.");
        reservationDto.setPayment(postDetail.getPricePerTeam());
        reservationDto.setReservationStatus(ReservationStatus.RESERVED);
        Reservation reservation = ReservationMapper.toReservationEntity(reservationDto);

        postDetail.changeAvailableCnt(reservation.getReservationStatus());
        reservationRepository.save(reservation);
    }

    // 예약 취소
    @Transactional
    public void cancelReservation(Long userId, Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new CustomException("없는 예약내역입니다."));
        if(reservation.getUserId() != userId) throw new CustomException("회원정보가 일치하지 않습니다.");
        PostDetail postDetail = postDetailRepository.findById(reservation.getPostDetailId()).orElseThrow(() -> new CustomException("에러가 발생"));

        reservation.changeReservationStatus(ReservationStatus.CANCELLED);
        postDetail.changeAvailableCnt(ReservationStatus.CANCELLED);
    }
}
