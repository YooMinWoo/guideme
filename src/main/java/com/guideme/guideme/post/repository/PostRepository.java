package com.guideme.guideme.post.repository;

import com.guideme.guideme.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
