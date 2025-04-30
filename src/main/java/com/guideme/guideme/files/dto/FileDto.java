package com.guideme.guideme.files.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

    private Long file_id;

    private String originalFileName;  // 사용자가 올린 원본 이름

    private String storedFileName;    // 저장된 이름 (UUID 등으로 난수화 추천)

    private String filePath;          // 저장 경로

    private Long fileSize;            // 파일 크기

    private String contentType;       // MIME 타입 (예: image/png)

    private LocalDateTime uploadedAt; // 업로드 시간
}
