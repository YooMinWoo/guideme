package com.guideme.guideme.reservation.repository;

import com.guideme.guideme.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.postId = :postId " +
            "AND (:startDate between r.startDate and r.endDate OR :endDate between r.startDate and r.endDate)")
    Optional<Reservation> findReservationStatus(@Param("postId") Long postId,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
}
