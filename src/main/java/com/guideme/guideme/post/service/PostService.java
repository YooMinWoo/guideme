package com.guideme.guideme.post.service;

import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.post.domain.*;
import com.guideme.guideme.post.dto.*;
import com.guideme.guideme.post.mapper.PostDetailMapper;
import com.guideme.guideme.post.mapper.PostMapper;
import com.guideme.guideme.post.repository.PostDetailRepository;
import com.guideme.guideme.post.repository.PostRepository;
import com.guideme.guideme.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostDetailRepository postDetailRepository;
    private final ReservationService reservationService;

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
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

    public ResponsePostDetailDto getPostDetail(Long postId, LocalDate startDate) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));
        PostDetail postDetail = postDetailRepository.findByPostIdAndStartDate(postId, startDate)
                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));
        Status status = Status.CLOSED;
        if(post.getStatus() == Status.OPEN && postDetail.getStatus() == Status.OPEN && postDetail.getAvailableCnt() > 0) status = Status.OPEN;

        return ResponsePostDetailDto.builder()
                .postDetailId(postDetail.getPostId())
                .title(post.getTitle())
                .description(post.getDescription())
                .startDate(postDetail.getStartDate())
                .pricePerTeam(postDetail.getPricePerTeam())
                .minPeople(post.getMinPeople())
                .maxPeople(post.getMaxPeople())
                .availableCnt(postDetail.getAvailableCnt())
                .status(status)
                .build();
    }

    public void createPost(CreatePostDto createPostDto) {
        PostDto postDto = createPostDto.getPostDto();
        postDto.setStatus(Status.OPEN);     // post의 초기상태 = OPEN
        List<PostDetailDto> postDetailDtoList = createPostDto.getPostDetailDtoList();
        Post post = PostMapper.toPostEntity(postDto);
        postRepository.save(post);
        for(PostDetailDto postDetailDto : postDetailDtoList){
            postDetailDto.setPostId(post.getId());
            postDetailDto.setStatus(Status.OPEN);
            postDetailDto.setAvailableCnt(postDetailDto.getTotalCnt());     // 초기에는 [예약 가능한 개수 = 총 개수]
            PostDetail postDetail = PostDetailMapper.ToPostDetailEntity(postDetailDto);
            postDetailRepository.save(postDetail);
        }
    }

    @Transactional
    public void updatePost(PostDto postDto){
        Post post = postRepository.findById(postDto.getPostId())
                .orElseThrow(() -> new CustomException("없는 게시물입니다."));
        if(post.getUserId() != postDto.getUserId()) throw new CustomException("회원정보가 일치하지 않습니다.");
        post.change(postDto);
    }

    public void deletePostDetail(PostDetailDto postDetailDto, Long userId) {
        Post post = postRepository.findById(postDetailDto.getPostId())
                .orElseThrow(() -> new CustomException("없는 게시물입니다."));
        if(post.getUserId() != userId) throw new CustomException("회원정보가 일치하지 않습니다.");
        PostDetail postDetail = postDetailRepository.findByPostIdAndStartDate(postDetailDto.getPostId(), postDetailDto.getStartDate())
                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));

        postDetailRepository.delete(postDetail);
    }

    @Transactional
    public void updatePostDetail(PostDetailDto postDetailDto, Long userId) {
        Post post = postRepository.findById(postDetailDto.getPostId())
                .orElseThrow(() -> new CustomException("없는 게시물입니다."));
        if(post.getUserId() != userId) throw new CustomException("회원정보가 일치하지 않습니다.");
        Optional<PostDetail> optPostDetail = postDetailRepository.findByPostIdAndStartDate(postDetailDto.getPostId(), postDetailDto.getStartDate());
        if(optPostDetail.isPresent()) {
            PostDetail postDetail = optPostDetail.get();
            postDetail.change(postDetailDto);
        } else {
            postDetailDto.setStatus(Status.OPEN);
            PostDetail postDetail = PostDetailMapper.ToPostDetailEntity(postDetailDto);
            postDetailRepository.save(postDetail);
        }
    }

}
