package com.guideme.guideme.post.repository;

import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.domain.PostDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PostDetailRepository extends JpaRepository<PostDetail,Long> {

    Optional<PostDetail> findByPostIdAndStartDate(Long postId, LocalDate startDate);
}
