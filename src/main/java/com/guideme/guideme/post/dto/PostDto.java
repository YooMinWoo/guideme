package com.guideme.guideme.post.dto;

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
public class PostDto {
    private Long postId;

    private Long userId;

    private String title;   // 제목
    private String description; // 설명

    private int minPeople;      // 최소 인원
    private int maxPeople;      // 최대 인원
}
