package com.guideme.guideme.reservation.domain;

import com.guideme.guideme.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity{
        @Id
        @GeneratedValue
        @Column(name = "reservation_id")
        private Long id;

        private Long postDetailId;
        private Long userId;

        private int price;      // 가격
        private int cnt;        // 인원 수

        public int getTotalPrice(){
                return price * cnt;
        }
}
