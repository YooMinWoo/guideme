package com.guideme.guideme.post.repository;

import com.guideme.guideme.post.domain.SeasonalPrice;
import com.guideme.guideme.post.domain.WeekdayPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekdayPriceRepository extends JpaRepository<WeekdayPrice,Long> {

}
