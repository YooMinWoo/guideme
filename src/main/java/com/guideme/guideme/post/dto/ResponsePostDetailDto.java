package com.guideme.guideme.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guideme.guideme.files.dto.FileResponse;
import com.guideme.guideme.post.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePostDetailDto {

    private Long postDetailId;

    private String title;
    private String description;

    private List<FileResponse> imgResponse;

    private String country;
    private String city;

    private String tradeName;  // 상호명
    private String businessMobile;  // 대표 전화번호
    private String businessEmail;  // 대표 이메일

    private LocalDate startDate;
    private int pricePerTeam;   // 날짜별 한 팀 당 가격
    private int minPeople;
    private int maxPeople;
    private int availableCnt;   // 이용 가능한 개수

    private boolean likesTF;

    private Status status;

}
