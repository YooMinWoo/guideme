package com.guideme.guideme.post.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.post.domain.ReservationStatus;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.post.service.PostService;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PreAuthorize("hasRole('ROLE_GUIDE')")
    @PostMapping("/post")
    public ResponseEntity<?> createPost(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody PostDto postDto){
        User user = customUserDetails.getUser();
        postDto.setUser_id(user.getId());
        postDto.setReservationStatus(ReservationStatus.OPEN);
        postService.createPost(postDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("등록 성공!", null));
    }
}
