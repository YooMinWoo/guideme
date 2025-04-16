package com.guideme.guideme.reservation.domain;

import com.guideme.guideme.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity{

        @Id
        @GeneratedValue
        @Column(name = "reservation_id")
        private Long id;

        private Long userId;
        private Long postDetailId;
        private int people;
        private int payment;

        @Enumerated(EnumType.STRING)
        private ReservationStatus reservationStatus;

        @Builder
        public Reservation(Long userId, Long postDetailId, int people, int payment, ReservationStatus reservationStatus) {
                this.userId = userId;
                this.postDetailId = postDetailId;
                this.people = people;
                this.payment = payment;
                this.reservationStatus = reservationStatus;
        }

        public void changeReservationStatus(ReservationStatus reservationStatus){
                this.reservationStatus = reservationStatus;
        }
}
