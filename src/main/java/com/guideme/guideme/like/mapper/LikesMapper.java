package com.guideme.guideme.like.mapper;

import com.guideme.guideme.like.domain.Likes;
import com.guideme.guideme.like.dto.LikesDto;

import java.util.ArrayList;
import java.util.List;

public class LikesMapper {

    public static List<LikesDto> toLikesDto(List<Likes> likesList){
        List<LikesDto> likesDtoList = new ArrayList<>();
        for(Likes likes : likesList){
            likesDtoList.add(LikesDto.builder()
                    .likesId(likes.getId())
                    .postDetailId(likes.getPostDetailId())
                    .build());
        }
        return likesDtoList;
    }
}
