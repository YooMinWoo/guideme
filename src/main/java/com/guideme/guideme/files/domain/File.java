package com.guideme.guideme.files.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class File {

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    private Long postId;             // 게시물 id

    private String originalFileName;  // 사용자가 올린 원본 이름

    private String storedFileName;    // 저장된 이름 (UUID 등으로 난수화 추천)

    private String filePath;          // 저장 경로

    private Long fileSize;            // 파일 크기

    private String contentType;       // MIME 타입 (예: image/png)

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate; // 업로드 시간

    public File(Long postId, String originalFileName, String storedFileName, String filePath, Long fileSize, String contentType) {
        this.postId = postId;
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.contentType = contentType;
    }
}
