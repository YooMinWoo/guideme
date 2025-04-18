package com.guideme.guideme.init;

import com.guideme.guideme.post.dto.CreatePostDto;
import com.guideme.guideme.post.dto.PostDetailDto;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostInit {

    private final PostService postService;

    public void createPost(Long userId){
        CreatePostDto createPostDto = new CreatePostDto();

        PostDto postDto = PostDto.builder()
                .userId(userId)
                .regionId(1L)
                .title("제목1")
                .description("내용1")
                .minPeople(5)
                .maxPeople(15)
                .build();
        List<PostDetailDto> postDetailDtoList = List.of(
                PostDetailDto.builder()
                        .startDate(LocalDate.parse("2025-04-15"))
                        .pricePerTeam(1000000)
                        .totalCnt(3)
                        .build(),
                PostDetailDto.builder()
                        .startDate(LocalDate.parse("2025-04-16"))
                        .pricePerTeam(1300000)
                        .totalCnt(6)
                        .build()
        );

        createPostDto.setPostDto(postDto);
        createPostDto.setPostDetailDtoList(postDetailDtoList);
        postService.createPost(createPostDto);
    }
}
