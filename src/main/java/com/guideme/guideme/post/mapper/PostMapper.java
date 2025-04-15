package com.guideme.guideme.post.mapper;

import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.dto.PostDto;

public class PostMapper {

    public static Post toPostEntity(PostDto postDto){

        return Post.builder()
                .userId(postDto.getUserId())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .minPeople(postDto.getMinPeople())
                .maxPeople(postDto.getMaxPeople())
                .status(postDto.getStatus())
                .build();
    }

//    public static PostDto toPostDto(Post post) {
//        return PostDto.builder()
//                .postId(post.getId())
//                .userId(post.getUserId())
//                .title(post.getTitle())
//                .description(post.getDescription())
//                .build();
//    }

}
