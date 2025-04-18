package com.guideme.guideme.region.repository;

import com.guideme.guideme.region.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    @Query("SELECT DISTINCT r.country FROM Region r")
    List<String> findDistinctCountries();

    @Query("select r.city from Region r where r.country = :country")
    List<String> findAllByCountry(@Param("country") String country);

//    Optional<Region> findByCountryAndCity
}
