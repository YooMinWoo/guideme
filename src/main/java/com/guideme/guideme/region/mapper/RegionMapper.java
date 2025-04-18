package com.guideme.guideme.region.mapper;

import com.guideme.guideme.region.domain.Region;
import com.guideme.guideme.region.dto.RegionDto;

public class RegionMapper {

    public static Region toRegionEntity(RegionDto regionDto){
        return Region.builder()
                .userId(regionDto.getUserId())
                .country(regionDto.getCountry())
                .city(regionDto.getCity())
                .build();
    }
}
