package com.guideme.guideme.post.mapper;

import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.domain.PostDetail;
import com.guideme.guideme.post.dto.PostDetailDto;
import com.guideme.guideme.post.dto.PostDto;

public class PostMapper {

    public static Post toPostEntity(PostDto postDto){
//        return Post.builder()
//                .user_id(postDto.getUser_id())
//                .title(postDto.getTitle())
//                .description(postDto.getDescription())
//                .adultPrice(postDto.getAdultPrice())
//                .childPrice(postDto.getChildPrice())
//                .postDate(postDto.getPostDate())
//                .reservationStatus(postDto.getReservationStatus())
//                .build();

        return Post.builder()
                .user_id(postDto.getUser_id())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .defaultPrice(postDto.getDefaultPrice())
                .status(postDto.getStatus())
                .build();
    }

    public static PostDto toPostDto(Post post) {
//        return PostDto.builder()
//                .post_id(post.getId())
//                .user_id(post.getUser_id())
//                .title(post.getTitle())
//                .description(post.getDescription())
//                .adultPrice(post.getAdultPrice())
//                .childPrice(post.getChildPrice())
//                .postDate(post.getPostDate())
//                .reservationStatus(post.getReservationStatus())
//                .build();

        return PostDto.builder()
                .post_id(post.getId())
                .user_id(post.getUser_id())
                .title(post.getTitle())
                .description(post.getDescription())
                .defaultPrice(post.getDefaultPrice())
                .status(post.getStatus())
                .build();
    }

    public static PostDetail toPostDetailEntity(PostDetailDto postDetailDto) {
        return PostDetail.builder()
                .user_id(postDetailDto.getUser_id())
                .post_id(postDetailDto.getPost_id())
                .price(postDetailDto.getPrice())
                .postDate(postDetailDto.getPostDate())
                .build();
    }
}
