package com.guideme.guideme.like.repository;

import com.guideme.guideme.like.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    // 좋아요 조회
    Optional<Likes> findByUserIdAndPostDetailId(Long userId, Long postDetailId);

//    List<Likes> findAllByPostId(Long postId);

    // 내가 누른 좋아요 조회
    List<Likes> findAllByUserId(Long userId);
}
