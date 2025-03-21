package com.guideme.guideme.post.service;

import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.post.domain.*;
import com.guideme.guideme.post.dto.*;
import com.guideme.guideme.post.mapper.PostMapper;
import com.guideme.guideme.post.repository.PostRepository;
import com.guideme.guideme.post.repository.SeasonalPriceRepository;
import com.guideme.guideme.post.repository.WeekdayPriceRepository;
import com.guideme.guideme.reservation.repository.ReservationRepository;
import com.guideme.guideme.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final SeasonalPriceRepository seasonalPriceRepository;
    private final WeekdayPriceRepository weekdayPriceRepository;
    private final ReservationService reservationService;

    public Optional<Post> findById(Long id){
        return postRepository.findById(id);
    }

    public void createPost(CreatePostDto createPostDto){

        Post post = PostMapper.toPostEntity(createPostDto.getPostDto());
        postRepository.save(post);
        if(createPostDto.getSeasonalPriceDto() != null){
            for(SeasonalPriceDto dto : createPostDto.getSeasonalPriceDto()){
                dto.setPostId(post.getId());
                SeasonalPrice seasonalPrice = PostMapper.toSeasonalPriceEntity(dto);
                seasonalPriceRepository.save(seasonalPrice);
            }
        }
        if(createPostDto.getWeekdayPriceDto() != null){
            for(WeekdayPriceDto dto : createPostDto.getWeekdayPriceDto()){
                dto.setPostId(post.getId());
                WeekdayPrice weekdayPrice = PostMapper.toWeekdayPriceEntity(dto);
                weekdayPriceRepository.save(weekdayPrice);
            }
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

    public PostDto getPostDetail(Long postId, LocalDate startDate, LocalDate endDate) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));
        PostDto postDto = PostMapper.toPostDto(post);
        postDto.setStatus(reservationService.postStatus(postId, startDate, endDate));
        for(LocalDate date : startDate.datesUntil(endDate.plusDays(1)).toList()){
            Optional<SeasonalPrice> seasonalPrice = seasonalPriceRepository.findByPostIdAndDate(postId, date);

            String weekday = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);


            // 성수기 등 특정 날짜의 가격이 있는지 확인 후 반환
            if(seasonalPrice.isPresent()){
                postDto.setPrice(postDto.getPrice() + seasonalPrice.get().getPrice());
                continue;
            }

            // 주말 등 특정 요일의 가격이 있는지 확인 후 반환
            Optional<WeekdayPrice> weekdayPrice = weekdayPriceRepository.findByPostIdAndWeekday(postId, Weekday.valueOf(weekday));
            if(weekdayPrice.isPresent()){
                postDto.setPrice(postDto.getPrice() + weekdayPrice.get().getPrice());
                continue;
            }
            postDto.setPrice(postDto.getPrice() + post.getPrice());
        }

        return postDto;

    }

    // post_detail 등록/수정
//    @Transactional
//    public void postDetail(PostDetailDto postDetailDto) {
//        Post post = postRepository.findById(postDetailDto.getPost_id())
//                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));
//        if(post.getUser_id() != postDetailDto.getUser_id()) throw new CustomException("본인만 변경할 수 있습니다.");
//
//        // 가격이 입력되지 않았을 경우, 기본 금액으로 입력
//        if(postDetailDto.getPrice() == 0) postDetailDto.setPrice(post.getDefaultPrice());
//
//        if(postDetailDto.getPost_detail_id() == null) { // 일자별 가격 등록
//            postDetailDto.setStatus(Status.OPEN);
//            PostDetail postDetail = PostMapper.toPostDetailEntity(postDetailDto);
//            postDetailRepository.save(postDetail);
//        } else {     // 이미 값이 등록되어 있어서, 수정을 하는 경우
//            Optional<PostDetail> postDetail = postDetailRepository.findById(postDetailDto.getPost_detail_id());
//            if(postDetail.isEmpty()) throw new CustomException("에러 발생");
//            postDetail.get().changePrice(postDetailDto.getPrice());
//        }
//    }
//
//    public PostDetailDto getPostDetail(Long id) {
//        PostDetail postDetail = postDetailRepository.findById(id)
//                .orElseThrow(() -> new CustomException("존재하지 않는 항목입니다."));
//        return PostMapper.toPostDetailDto(postDetail);
//    }
}
