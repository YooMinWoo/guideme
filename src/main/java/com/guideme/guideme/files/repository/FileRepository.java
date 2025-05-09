package com.guideme.guideme.files.repository;

import com.guideme.guideme.files.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository  extends JpaRepository<File, Long> {

    Optional<File> findByStoredFileName(String fileName);

    List<File> findByPostId(Long postId);
}
