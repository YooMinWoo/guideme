package com.guideme.guideme.post.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.post.domain.Status;
import com.guideme.guideme.post.dto.*;
import com.guideme.guideme.post.service.PostService;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Tag(name = "Post Controller", description = "가이드 게시글 관련 컨트롤러입니다.")
public class PostController {

    private final PostService postService;

    // 게시글 등록
    @Operation(summary = "게시글 등록", description = "게시글을 등록하는 api입니다. (가이드 권한만 접근 가능)")
    @PreAuthorize("hasRole('GUIDE')")
    @PostMapping(value = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPostWithImages(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                  @RequestPart("data") CreatePostDto createPostDto,
                                                  @RequestPart("files") MultipartFile[] files){
        User user = customUserDetails.getUser();
        createPostDto.getPostDto().setUserId(user.getId());
        postService.createPostWithImages(createPostDto, files);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("등록 성공!", null));
    }


    // 게시글 수정
    @Operation(summary = "게시글 수정", description = "게시글을 수정하는 api입니다. (가이드, 작성자 본인만 접근 가능)")
    @PreAuthorize("hasRole('GUIDE')")
    @PutMapping(value = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @RequestPart("data") UpdatePostDto updatePostDto,
                                        @RequestPart(name = "files", required = false) MultipartFile[] files){
        User user = customUserDetails.getUser();
        updatePostDto.setUserId(user.getId());
        postService.updatePost(updatePostDto, files);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("success!", null));
    }

    // 게시글 삭제
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제하는 api입니다. (가이드, 작성자 본인만 접근 가능)")
    @PreAuthorize("hasRole('GUIDE')")
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        @PathVariable("postId") Long postId){
        User user = customUserDetails.getUser();
        postService.deletePost(postId, user.getId());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("success!", null));
    }

    // 게시글 특정 날짜 가격 등록 or 수정
    @Operation(
            summary = "게시글 특정 날짜 가격 등록 또는 수정",
            description = "게시글에 특정 날짜에 대한 가격 정보를 등록 또는 수정하는 API입니다. (가이드, 작성자 본인만 접근 가능)"
    )
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
    @Operation(
            summary = "게시글 특정 날짜 가격 삭제",
            description = "게시글에 설정된 특정 날짜 가격 정보를 삭제하는 API입니다. (가이드, 작성자 본인만 접근 가능)"
    )
    @PreAuthorize("hasRole('GUIDE')")
    @DeleteMapping("/post/{postId}/{startDate}")
    public ResponseEntity<?> deletePostDetail(@PathVariable("postId") Long postId,
                                        @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();
        postService.deletePostDetail(postId, startDate, user.getId());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("삭제 성공!", null));
    }

    // 게시글 조회
    @Operation(
            summary = "게시글 조회",
            description = "게시글 상세 정보를 조회하는 API입니다."
    )
    @GetMapping("/post/{postId}/{startDate}")
    public ResponseEntity<?> getPostDetail(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                           @PathVariable("postId") Long postId,
                                           @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate){
        Long userId = (customUserDetails != null) ? customUserDetails.getUser().getId() : null;
        ResponsePostDetailDto responsePostDetailDto = postService.getPostDetail(userId, postId, startDate);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("조회 성공!", responsePostDetailDto));
    }

}
