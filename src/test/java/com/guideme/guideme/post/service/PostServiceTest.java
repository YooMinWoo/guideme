package com.guideme.guideme.post.service;

import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.dto.PostDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired PostService postService;
    @Autowired EntityManager em;


    // update 테스트
//    @Test
//    public void updateTest(){
//        //given
//        PostDto postDto = new PostDto();
//        postDto.setTitle("원래 제목");
//
//        Post post = postService.createPost(postDto);
//        em.flush();
//        em.clear();
//
//        //when
//        Post findPost1 = postService.findById(post.getId()).get();
//        String title = "바뀐 제목";
//        findPost1.changeTitle(title);
//
//        em.flush();
//        em.clear();
//
//        //then
//        Post findPost2 = postService.findById(post.getId()).get();
//        assertThat(findPost2.getTitle()).isEqualTo(title);
//    }
}