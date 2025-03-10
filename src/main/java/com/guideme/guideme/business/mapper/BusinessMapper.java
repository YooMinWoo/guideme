package com.guideme.guideme.business.mapper;

import com.guideme.guideme.business.domain.Business;
import com.guideme.guideme.business.dto.BusinessDto;

public class BusinessMapper {

    public static Business toBusinessEntity(BusinessDto businessDto){
        return Business.builder()
                .userId(businessDto.getUserId())
                .tradeName(businessDto.getTradeName())
                .businessEmail(businessDto.getBusinessEmail())
                .businessMobile(businessDto.getBusinessMobile())
                .registrationNumber(businessDto.getRegistrationNumber())
                .information(businessDto.getInformation())
                .build();
    }
}
