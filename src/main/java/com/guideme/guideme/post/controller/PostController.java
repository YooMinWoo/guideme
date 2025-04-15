package com.guideme.guideme.post.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.post.domain.Status;
import com.guideme.guideme.post.dto.CreatePostDto;
import com.guideme.guideme.post.dto.PostDetailDto;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.post.dto.ResponsePostDetailDto;
import com.guideme.guideme.post.service.PostService;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

//    @PreAuthorize("hasRole('GUIDE')")
//    @PutMapping("/post")
//    public ResponseEntity<?> updatePost(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody PostDto postDto){
//        User user = customUserDetails.getUser();
//        postDto.setUser_id(user.getId());
//        postDto.setStatus(Status.OPEN);
//        postService.createPost(postDto);
//        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("등록 성공!", null));
//    }


    // 게시글 등록
    @PreAuthorize("hasRole('GUIDE')")
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody CreatePostDto createPostDto){
        User user = customUserDetails.getUser();
        createPostDto.getPostDto().setUserId(user.getId());
        postService.createPost(createPostDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("등록 성공!", null));
    }


    // 게시글 제목/내용 변경
    @PreAuthorize("hasRole('GUIDE')")
    @PutMapping("/post")
    public ResponseEntity<?> postDetail(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody PostDto postDto){
        User user = customUserDetails.getUser();
        postDto.setUserId(user.getId());
        postService.updatePost(postDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("success!", null));
    }

    // 게시글 특정 날짜 가격 등록 or 수정
    @PreAuthorize("hasRole('GUIDE')")
    @PutMapping("/post/{postId}/{startDate}")
    public ResponseEntity<?> postDetail(@PathVariable("postId") Long postId,
                                        @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @RequestBody PostDetailDto postDetailDto){
        User user = customUserDetails.getUser();
        postDetailDto.setPostId(postId);
        postDetailDto.setStartDate(startDate);
        postService.updatePostDetail(postDetailDto, user.getId());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("success!", null));
    }

    // PostDetail 삭제
    @PreAuthorize("hasRole('GUIDE')")
    @DeleteMapping("/post/{postId}/{startDate}")
    public ResponseEntity<?> deletePostDetail(@PathVariable("postId") Long postId,
                                        @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @RequestBody PostDetailDto postDetailDto){
        /*
            해당 postDetail을 마감처리 한다.
         */
        postDetailDto.setPostId(postId);
        postDetailDto.setStartDate(startDate);
        User user = customUserDetails.getUser();
        postService.deletePostDetail(postDetailDto, user.getId());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("삭제 성공!", null));
    }

    // 게시글 조회
    @GetMapping("/post/{postId}/{startDate}")
    public ResponseEntity<?> getPostDetail(@PathVariable("postId") Long postId,
                                           @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate){
        ResponsePostDetailDto responsePostDetailDto = postService.getPostDetail(postId, startDate);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("조회 성공!", responsePostDetailDto));
    }

}
