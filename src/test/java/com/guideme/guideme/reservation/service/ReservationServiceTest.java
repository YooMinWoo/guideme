package com.guideme.guideme.reservation.service;

import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.reservation.dto.ReservationDto;
import com.guideme.guideme.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class ReservationServiceTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void reservationTest(){
        // given
        ReservationDto reservationDto = new ReservationDto();
        Post post = Post.builder()
                .title("test1")
                .price(50000)
                .build();
        reservationDto.setStart_date(LocalDate.parse("2025-04-03"));
        reservationDto.setEnd_date(LocalDate.parse("2025-04-08"));


    }
}
