package com.guideme.guideme.like.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.like.domain.Likes;
import com.guideme.guideme.like.dto.LikesDto;
import com.guideme.guideme.like.service.LikesService;
import com.guideme.guideme.security.user.CustomUserDetails;
import com.guideme.guideme.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Like Controller", description = "좋아요 컨트롤러입니다.")
public class LikesController {

    private final LikesService likesService;

    // 좋아요 누르기
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/likes")
    @Operation(
            summary = "좋아요 누르기",
            description = "좋아요 안 누른 게시글일 경우 좋아요 check" +
                    "좋아요 누른 게시글일 경우 좋아요 uncheck (유저 권한만 접근 가능)"
    )
    public ResponseEntity<?> pushLikes(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       @RequestParam(name = "postDetailId") Long postDetailId){
        User user = customUserDetails.getUser();
        likesService.likes(user.getId(), postDetailId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("좋아요 성공!", null));
    }

    // 내가 좋아요 한 리스트
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/likes")
    @Operation(
            summary = "좋아요 리스트",
            description = "좋아요 리스트 조회하는 API입니다. (유저 권한만 접근 가능)"
    )
    public ResponseEntity<?> getLikesPost(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();
        List<LikesDto> likesDtoList = likesService.likesList(user.getId());
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("내가 누른 좋아요 조회", likesDtoList));
    }

}
