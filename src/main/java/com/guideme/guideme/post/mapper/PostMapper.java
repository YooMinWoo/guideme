package com.guideme.guideme.post.mapper;

import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.dto.PostDto;

public class PostMapper {

    public static Post toPostEntity(PostDto postDto){
        return Post.builder()
                .user_id(postDto.getUser_id())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .adultPrice(postDto.getAdultPrice())
                .childPrice(postDto.getChildPrice())
                .postDate(postDto.getPostDate())
                .reservationStatus(postDto.getReservationStatus())
                .build();
    }
}
