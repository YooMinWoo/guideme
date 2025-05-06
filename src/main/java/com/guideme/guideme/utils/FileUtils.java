package com.guideme.guideme.utils;

import com.guideme.guideme.files.domain.File;
import com.guideme.guideme.files.dto.FileDto;
import com.guideme.guideme.global.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUtils {

//    private static final String UPLOAD_PATH = "C:\\Users\\CODELINE\\Desktop\\dummyFiles\\";
//    private static final String UPLOAD_PATH = "C:\\Users\\MINU\\Desktop\\dummyFiles\\";

    @Value("${server.fileLocate}")
    private String fileLocate;

    public File uploadFile(MultipartFile multipartFile, Long post_id){
        if (multipartFile.isEmpty()) throw new CustomException("첨부파일이 없네요잉");

        try {
            // 저장할 경로 생성
            Path uploadPath = Paths.get(fileLocate);
            if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

            String originalFileName = multipartFile.getOriginalFilename();
            String storedFileName = UUID.randomUUID() + "_" + originalFileName;
            long fileSize = multipartFile.getSize();
            String contentType = multipartFile.getContentType();

            if(!contentType.contains("image")) throw new CustomException("업로드 할 수 없는 파일의 형식입니다.");

            FileDto fileDto = new FileDto();
            fileDto.setPost_id(post_id);
            fileDto.setOriginalFileName(originalFileName);
            fileDto.setStoredFileName(storedFileName);
            fileDto.setFilePath(fileLocate);
            fileDto.setFileSize(fileSize);
            fileDto.setContentType(contentType);

            File file = new File(
                    fileDto.getPost_id(),
                    fileDto.getOriginalFileName(),
                    fileDto.getStoredFileName(),
                    fileDto.getFilePath(),
                    fileDto.getFileSize(),
                    fileDto.getContentType()
            );

            Path targetPath = uploadPath.resolve(storedFileName);
            multipartFile.transferTo(targetPath.toFile());
            return file;
        } catch (IOException e) {
            throw new CustomException("파일 첨부를 실패했네요잉");
        }
    }

    public void deleteFile(File file) {
        try {
            Path filePath = Paths.get(file.getFilePath()).resolve(file.getStoredFileName());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new CustomException("파일 삭제 중 에러 발생");
        }
    }
}
