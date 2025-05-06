package com.guideme.guideme.files.service;

import com.guideme.guideme.files.domain.File;
import com.guideme.guideme.files.repository.FileRepository;
import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final FileUtils fileUtils;

//    public FileResponse getImage(String fileName){
//        try{
//            File file = fileRepository.findByStoredFileName(fileName)
//                    .orElseThrow(() -> new CustomException("파일 없음"));
//
//            Path path = Paths.get(file.getFilePath(), file.getStoredFileName());
//            Resource resource = new UrlResource(path.toUri());
//            if (!resource.exists()) throw new CustomException("파일이 존재하지 않습니다");
//            return FileResponse.builder()
//                    .resource(resource)
//                    .contentType(file.getContentType())
//                    .build();
//        } catch (Exception e){
//            throw new CustomException("파일 조회 중 에러 발생");
//        }
//
//    }

//    @Transactional
//    public void uploadFile(MultipartFile[] files){
//        for(MultipartFile multipartFile : files){
//            File file = fileUtils.uploadFile(multipartFile, post.getId());
//            fileRepository.save(file);
//        }
}
