package com.guideme.guideme.reservation.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.reservation.domain.Reservation;
import com.guideme.guideme.reservation.dto.ReservationDto;
import com.guideme.guideme.reservation.dto.ResponseReservation;
import com.guideme.guideme.reservation.service.ReservationService;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.dto.SignupDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Reservation Controller", description = "예약 컨트롤러입니다.")
public class ReservationController {

    private final ReservationService reservationService;

    // 예약 내역 확인
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/reservation")
    @Operation(
            summary = "예약 내역 조회",
            description = "본인의 예약 내역을 조회하는 API입니다. (유저 권한만 접근 가능)"
    )
    public ResponseEntity<?> reservationHistory(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        Long userId = customUserDetails.getUser().getId();
        List<ResponseReservation> responseReservationList = reservationService.getReservations(userId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("예약 내역!", responseReservationList));
    }


    // 예약하기
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/reservation/{postDetailId}")
    @Operation(
            summary = "예약하기",
            description = "예약 API입니다. (유저 권한만 접근 가능)"
    )
    public ResponseEntity<?> reservation(@PathVariable("postDetailId") Long postDetailId,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                         @RequestBody ReservationDto reservationDto){
        reservationDto.setUserId(customUserDetails.getUser().getId());
        reservationDto.setPostDetailId(postDetailId);
        reservationService.reservation(reservationDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("예약 완료", null));
    }

    // 예약 취소
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/reservation/{reservationId}")
    @Operation(
            summary = "예약 취소",
            description = "예약 취소 API입니다. (유저, 예약자 본인만 접근 가능)"
    )
    public ResponseEntity<?> cancelReservation(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                               @PathVariable("reservationId") Long reservationId){
        Long userId = customUserDetails.getUser().getId();
        reservationService.cancelReservation(userId, reservationId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("예약 취소 완료", null));
    }
}
