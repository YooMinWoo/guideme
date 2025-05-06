package com.guideme.guideme.files.controller;

import com.guideme.guideme.files.dto.FileDto;
import com.guideme.guideme.files.service.FileService;
import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.user.dto.TokenDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    /*
    테스트 완료. 업로드 및 db 저장까지 무사히 됨.

    Post와 연동하기 (post_id를 File 엔티티가 참조하여 저장.)
    Post 조회시 사진 조회하기
     */


    // 사진 업로드
//    @PostMapping("upload-file")
//    public ResponseEntity<?> handleMultiFileUpload(@RequestParam("files") MultipartFile[] files) {
//        fileService.uploadFile(files);
//
//        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("파일 업로드 성공!", null));
//    }

    // 사진 조회
//    @GetMapping("/image/{fileName}")
//    public ResponseEntity<Resource> getImage(@PathVariable("fileName") String fileName) {
//        FileResponse fileResponse = fileService.getImage(fileName);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(fileResponse.getContentType()))
//                .body(fileResponse.getResource());
//    }

}
