package com.guideme.guideme.init;

import com.guideme.guideme.region.dto.RegionDto;
import com.guideme.guideme.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionInit {

    private final RegionService regionService;

    public void createRegion(Long userId){
        String[] country = {"일본","일본","일본","한국","한국","한국","말레이시아"};
        String[] city = {"도쿄","나고야","후쿠오카","서울","부산","여수","코타키나발루"};

        for(int i = 0; i < country.length; i++){
            RegionDto regionDto = RegionDto.builder()
                    .userId(userId)
                    .country(country[i])
                    .city(city[i])
                    .build();
            regionService.createRegion(regionDto);
        }

    }
}
