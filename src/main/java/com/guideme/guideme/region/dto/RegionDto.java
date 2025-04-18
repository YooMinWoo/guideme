package com.guideme.guideme.region.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionDto {
    private Long regionId;
    private Long userId;

    private String country;  // "일본", "한국"
    private String city;     // "도쿄", "후쿠오카", "서울"

}
