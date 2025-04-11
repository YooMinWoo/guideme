package com.guideme.guideme.reservation.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.reservation.domain.Reservation;
import com.guideme.guideme.reservation.dto.ReservationDto;
import com.guideme.guideme.reservation.service.ReservationService;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/reservation")
    public ResponseEntity<?> reservationHistory(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        Long userId = customUserDetails.getUser().getId();
        List<ReservationDto> reservationDtoList = reservationService.getReservations(userId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("예약 내역!", reservationDtoList));
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/reservation/{postDetailId}")
    public ResponseEntity<?> reservation(@PathVariable("postDetailId") Long postDetailId,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                         @RequestBody ReservationDto reservationDto){
        reservationDto.setUserId(customUserDetails.getUser().getId());
        reservationDto.setPostDetailId(postDetailId);
        reservationService.reservation(reservationDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("예약 완료", null));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/reservation/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable("reservationId") Long reservationId,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails){
        Long userId = customUserDetails.getUser().getId();
        reservationService.cancelReservation(userId, reservationId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("예약 취소 완료", null));
    }
}
