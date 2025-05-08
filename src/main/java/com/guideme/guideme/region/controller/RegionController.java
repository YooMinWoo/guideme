package com.guideme.guideme.region.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.region.dto.RegionDto;
import com.guideme.guideme.region.dto.ResponseRegionDto;
import com.guideme.guideme.region.service.RegionService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Region Controller", description = "지역 관련 컨트롤러입니다.")
public class RegionController {

    private final RegionService regionService;

    @Operation(
            summary = "지역 조회",
            description = "지역 정보를 조회하는 API입니다."
    )
    @GetMapping("/region")
    public ResponseEntity<?> getRegion(){
        List<ResponseRegionDto> responseRegionDtoList = regionService.getRegion();
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("지역 조회 성공!", responseRegionDtoList));
    }

    @Operation(
            summary = "지역 등록",
            description = "지역을 추가하는 API입니다. (관리자 권한만 접근 가능)"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/region")
    public ResponseEntity<?> createRegion(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                          @RequestBody RegionDto regionDto){
        User user = customUserDetails.getUser();
        regionDto.setUserId(user.getId());
        regionService.createRegion(regionDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("지역 추가 성공!", regionDto));
    }
}
