package com.guideme.guideme.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guideme.guideme.post.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePostDetailDto {

    private Long postDetailId;

    private String title;
    private String description;

    private LocalDate startDate;
    private int pricePerTeam;   // 날짜별 한 팀 당 가격
    private int minPeople;
    private int maxPeople;
    private Status status;

}
