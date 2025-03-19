package com.guideme.guideme.post.repository;

import com.guideme.guideme.post.domain.SeasonalPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SeasonalPriceRepository extends JpaRepository<SeasonalPrice,Long> {
    Optional<SeasonalPrice> findByPostIdAndDate(Long postId, LocalDate date);
}
