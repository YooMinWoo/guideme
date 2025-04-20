package com.guideme.guideme.like.service;

import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.service.PostService;
import com.guideme.guideme.user.domain.User;
import com.guideme.guideme.user.service.UserService;
//import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class LikesServiceTest {

    @Autowired LikesService likesService;
    @Autowired UserService userService;
    @Autowired PostService postService;

//    @Test
//    public void likePlus(){
//        User user = userService.findByUsername("alsn97").get();
//        Post post = postService.findById(1L).get();
//        int beforeLikeCnt = likesService.likesCnt(post.getId());
//
//        likesService.likes(user.getId(),post.getId());
//
//        int afterLikeCnt = likesService.likesCnt(post.getId());
//
//        assertThat(afterLikeCnt).isEqualTo(beforeLikeCnt+1);
//    }
}