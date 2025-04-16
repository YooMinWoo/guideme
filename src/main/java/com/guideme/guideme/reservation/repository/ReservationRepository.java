package com.guideme.guideme.reservation.repository;

import com.guideme.guideme.reservation.domain.Reservation;
import com.guideme.guideme.reservation.domain.ReservationStatus;
import com.guideme.guideme.reservation.dto.ReservationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);

    @Query(value = """
            Select r.* FROM reservation r
            JOIN post_detail pd ON r.post_detail_id = pd.post_detail_id
            WHERE pd.post_id = :postId
            AND r.reservation_status = 'RESERVED'
            """, nativeQuery = true)
    List<Reservation> findReservationByPostIdAndReserved(@Param("postId") Long postId);

}
