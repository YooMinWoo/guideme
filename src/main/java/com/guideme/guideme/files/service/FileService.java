package com.guideme.guideme.files.service;

import com.guideme.guideme.files.domain.File;
import com.guideme.guideme.files.dto.FileDto;
import com.guideme.guideme.files.dto.FileResponse;
import com.guideme.guideme.files.repository.FileRepository;
import com.guideme.guideme.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final String UPLOAD_PATH = "C:\\Users\\MINU\\Desktop\\dummyFiles\\";
    private final FileRepository fileRepository;

    public FileResponse getImage(String fileName){
        try{
            File file = fileRepository.findByStoredFileName(fileName)
                    .orElseThrow(() -> new CustomException("파일 없음"));

            Path path = Paths.get(file.getFilePath(), file.getStoredFileName());
            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists()) throw new CustomException("파일이 존재하지 않습니다");
            return FileResponse.builder()
                    .resource(resource)
                    .contentType(file.getContentType())
                    .build();
        } catch (Exception e){
            throw new CustomException("파일 조회 중 에러 발생");
        }

    }

    @Transactional
    public void uploadFile(MultipartFile[] files){
        for(MultipartFile multipartFile : files){
            if (multipartFile.isEmpty()) {
                throw new CustomException("첨부파일이 없네요잉");
            }

            try {
                // 저장할 경로 생성
                Path uploadPath = Paths.get(UPLOAD_PATH);
                if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

                String originalFileName = multipartFile.getOriginalFilename();
                String storedFileName = UUID.randomUUID() + "_" + originalFileName;
                long fileSize = multipartFile.getSize();
                String contentType = multipartFile.getContentType();

                if(!contentType.contains("image")) throw new CustomException("업로드 할 수 없는 파일의 형식입니다.");

                FileDto fileDto = new FileDto();
                fileDto.setOriginalFileName(originalFileName);
                fileDto.setStoredFileName(storedFileName);
                fileDto.setFilePath(UPLOAD_PATH);
                fileDto.setFileSize(fileSize);
                fileDto.setContentType(contentType);

                File file = new File(
                        fileDto.getOriginalFileName(),
                        fileDto.getStoredFileName(),
                        fileDto.getFilePath(),
                        fileDto.getFileSize(),
                        fileDto.getContentType()
                );

                fileRepository.save(file);

                Path targetPath = uploadPath.resolve(storedFileName);
                multipartFile.transferTo(targetPath.toFile());

            } catch (IOException e) {
                throw new CustomException("파일 첨부를 실패했네요잉");
            }
        }
    }
}
