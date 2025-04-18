package com.guideme.guideme.post.repository;

import com.guideme.guideme.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("select p from Post p where p.title like %:keyword% or p.description like %:keyword%")
    Page<Post> getSearchList(Pageable pageable, @Param("keyword") String keyword);
}
