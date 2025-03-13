package com.guideme.guideme.post.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.post.domain.Status;
import com.guideme.guideme.post.dto.CreatePostDto;
import com.guideme.guideme.post.dto.PostDetailDto;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.post.service.PostService;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PreAuthorize("hasRole('GUIDE')")
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody CreatePostDto createPostDto){
        User user = customUserDetails.getUser();
        createPostDto.getPostDto().setUser_id(user.getId());
        createPostDto.getPostDto().setStatus(Status.OPEN);
        postService.createPost(createPostDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("등록 성공!", null));
    }

    @PreAuthorize("hasRole('GUIDE')")
    @PostMapping("/postDetail")
    public ResponseEntity<?> postDetail(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody PostDetailDto postDetailDto){
        User user = customUserDetails.getUser();
        postDetailDto.setUser_id(user.getId());
        postService.postDetail(postDetailDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("등록 성공!", null));
    }



    @GetMapping("/post")
    public ResponseEntity<?> getPostList(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                         @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword,
                                         @RequestParam(name = "sort", defaultValue = "latest", required = false) String sortType){
        Page<PostDto> posts = postService.getSearchList(pageable,keyword,sortType);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("조회 성공!", posts));
    }

//    @PostConstruct
//    public void init(){
//        for(int i=0; i<100; i++){
//            PostDto postDto = new PostDto();
//            postDto.setTitle("게시물"+i);
//            postService.createPost(postDto);
//        }
//    }
}
