package com.guideme.guideme.reservation.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.reservation.domain.Reservation;
import com.guideme.guideme.reservation.dto.ReservationDto;
import com.guideme.guideme.reservation.service.ReservationService;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<?> reservation(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                         @RequestBody ReservationDto requestReservationDto){
        requestReservationDto.setUserId(customUserDetails.getUser().getId());
        ReservationDto reservationDto = reservationService.reservation(requestReservationDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("예약 완료", reservationDto));
    }
}
