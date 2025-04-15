package com.guideme.guideme.post.mapper;

import com.guideme.guideme.post.domain.PostDetail;
import com.guideme.guideme.post.dto.PostDetailDto;

public class PostDetailMapper {

    public static PostDetail ToPostDetailEntity(PostDetailDto postDetailDto){
        return
                PostDetail.builder()
                .postId(postDetailDto.getPostId())
                .startDate(postDetailDto.getStartDate())
                .pricePerTeam(postDetailDto.getPricePerTeam())
                .status(postDetailDto.getStatus())
                .totalCnt(postDetailDto.getTotalCnt())
                .availableCnt(postDetailDto.getAvailableCnt())
                .build();

    }
}
