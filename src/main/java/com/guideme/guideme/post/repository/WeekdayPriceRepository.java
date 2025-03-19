package com.guideme.guideme.post.repository;

import com.guideme.guideme.post.domain.SeasonalPrice;
import com.guideme.guideme.post.domain.Weekday;
import com.guideme.guideme.post.domain.WeekdayPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeekdayPriceRepository extends JpaRepository<WeekdayPrice,Long> {
    Optional<WeekdayPrice> findByPostIdAndWeekday(Long postId, Weekday weekday);
}
