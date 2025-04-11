package com.guideme.guideme.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guideme.guideme.post.domain.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailDto {

    private Long postDetailId;

    private Long postId;

    private LocalDate startDate;
    private int pricePerTeam;   // 날짜별 한 팀 당 가격

    private Integer cnt;    // 개수
    private Status status;

    public PostDetailDto(Long postId, LocalDate startDate, Integer cnt) {
        this.postId = postId;
        this.startDate = startDate;
        this.cnt = cnt;
    }
}
