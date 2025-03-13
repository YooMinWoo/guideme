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
public class PostDetailDto {

    private Long post_detail_id;

    private Long user_id;
    private Long post_id;

    private int price;  // 요금
    private LocalDate postDate; // 날짜
}
