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

    private Long post_id;

    private Long user_id;

    private String title;   // 제목
    private String description; // 설명
    private int defaultPrice;   // 기본 가격
    private Status status; // 상태

//    private int adultPrice;  // 대인 요금
//    private int childPrice;  // 소인 요금
//    private LocalDate postDate; // 날짜
}
