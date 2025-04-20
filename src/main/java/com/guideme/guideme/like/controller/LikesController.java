package com.guideme.guideme.like.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.like.domain.Likes;
import com.guideme.guideme.like.dto.LikesDto;
import com.guideme.guideme.like.service.LikesService;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    // 좋아요 누르기
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/likes")
    public ResponseEntity<?> pushLikes(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       @RequestParam(name = "postDetailId") Long postDetailId){
        User user = customUserDetails.getUser();
        likesService.likes(user.getId(), postDetailId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("좋아요 성공!", null));
    }

    // 내가 좋아요 한 리스트
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/likes")
    public ResponseEntity<?> getLikesPost(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();
        List<LikesDto> likesDtoList = likesService.likesList(user.getId());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("내가 누른 좋아요 조회", likesDtoList));
    }

}
