package com.guideme.guideme.post.dto;

import com.guideme.guideme.post.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostDto {
    private Long postId;

    private List<Long> deleteImgIdList;

    private Long userId;
    private Long regionId;      // 지역 도메인

    private String title;   // 제목
    private String description; // 설명

    private int minPeople;      // 최소 인원
    private int maxPeople;      // 최대 인원

    private Status status;
}