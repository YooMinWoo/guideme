package com.guideme.guideme.post.service;

import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.post.mapper.PostMapper;
import com.guideme.guideme.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(PostDto postDto){
        Post post = PostMapper.toPostEntity(postDto);
        postRepository.save(post);
    }
}
