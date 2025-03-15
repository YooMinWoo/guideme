package com.guideme.guideme.post.service;

import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.domain.PostDetail;
import com.guideme.guideme.post.dto.CreatePostDto;
import com.guideme.guideme.post.dto.PostDetailDto;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.post.mapper.PostMapper;
import com.guideme.guideme.post.repository.PostDetailRepository;
import com.guideme.guideme.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostDetailRepository postDetailRepository;

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }

    public void createPost(CreatePostDto createPostDto){

        Post post = PostMapper.toPostEntity(createPostDto.getPostDto());
        postRepository.save(post);
        if(createPostDto.getPostDetailDto() == null) return;

        for(PostDetailDto dto : createPostDto.getPostDetailDto()){
            dto.setUser_id(post.getUser_id());
            dto.setPost_id(post.getId());
            if(dto.getPrice() == 0) dto.setPrice(post.getDefaultPrice());
            PostDetail postDetail = PostMapper.toPostDetailEntity(dto);
            postDetailRepository.save(postDetail);
        }
    }

    public Page<PostDto> getSearchList(Pageable pageable, String keyword, String sortType){
        Pageable sortedPageable = applySorting(pageable, sortType);
        Page<Post> searchList = postRepository.getSearchList(sortedPageable, keyword);
        return searchList.map(PostMapper::toPostDto);
    }

    private Pageable applySorting(Pageable pageable, String sortType) {
        Sort sort;
        switch (sortType.toLowerCase()) {
            case "oldest":
                sort = Sort.by(Sort.Direction.ASC, "createdDate");
                break;
            case "popular":
                sort = Sort.by(Sort.Direction.DESC, "viewCount");
                break;
            default:
                sort = Sort.by(Sort.Direction.DESC, "createdDate");
        }
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    @Transactional
    public void postDetail(PostDetailDto postDetailDto) {
        Optional<Post> post = postRepository.findById(postDetailDto.getPost_id());
        if(post.isEmpty()) throw new CustomException("에러 발생");
        if(post.get().getUser_id() != postDetailDto.getUser_id()) throw new CustomException("본인만 변경할 수 있습니다.");

        if(postDetailDto.getPrice() == 0) postDetailDto.setPrice(post.get().getDefaultPrice());
        if(postDetailDto.getPost_detail_id() == null) { // 일자별 가격 등록
            PostDetail postDetail = PostMapper.toPostDetailEntity(postDetailDto);
            postDetailRepository.save(postDetail);
        } else {     // 이미 값이 등록되어 있어서, 수정을 하는 경우
            Optional<PostDetail> postDetail = postDetailRepository.findById(postDetailDto.getPost_detail_id());
            if(postDetail.isEmpty()) throw new CustomException("에러 발생");
            postDetail.get().changePrice(postDetailDto.getPrice());
        }
    }
}
