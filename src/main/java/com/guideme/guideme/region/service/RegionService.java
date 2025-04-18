package com.guideme.guideme.region.service;

import com.guideme.guideme.region.domain.Region;
import com.guideme.guideme.region.dto.RegionDto;
import com.guideme.guideme.region.dto.ResponseRegionDto;
import com.guideme.guideme.region.mapper.RegionMapper;
import com.guideme.guideme.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public List<ResponseRegionDto> getRegion() {
        List<ResponseRegionDto> result = new ArrayList<>();
        List<String> countryList = regionRepository.findDistinctCountries();
        for(String country : countryList){
            List<String> cityList = regionRepository.findAllByCountry(country);
            ResponseRegionDto responseRegionDto = new ResponseRegionDto(country, cityList);
            result.add(responseRegionDto);
        }
        return result;
    }

    @Transactional
    public void createRegion(RegionDto regionDto) {
        Region region = RegionMapper.toRegionEntity(regionDto);
        regionRepository.save(region);
    }
}
