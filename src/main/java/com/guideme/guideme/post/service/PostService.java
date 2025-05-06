package com.guideme.guideme.post.service;

import com.guideme.guideme.business.domain.Business;
import com.guideme.guideme.business.repository.BusinessRepository;
import com.guideme.guideme.files.domain.File;
import com.guideme.guideme.files.dto.FileResponse;
import com.guideme.guideme.files.repository.FileRepository;
import com.guideme.guideme.global.exception.CustomException;
import com.guideme.guideme.like.repository.LikesRepository;
import com.guideme.guideme.post.domain.*;
import com.guideme.guideme.post.dto.*;
import com.guideme.guideme.post.mapper.PostDetailMapper;
import com.guideme.guideme.post.mapper.PostMapper;
import com.guideme.guideme.post.repository.PostDetailRepository;
import com.guideme.guideme.post.repository.PostRepository;
import com.guideme.guideme.region.domain.Region;
import com.guideme.guideme.region.repository.RegionRepository;
import com.guideme.guideme.reservation.domain.Reservation;
import com.guideme.guideme.reservation.repository.ReservationRepository;
import com.guideme.guideme.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${server.url}")
    private String serverUrl;

    @Value("${server.fileSrc}")
    private String fileSrc;

    private final PostRepository postRepository;
    private final PostDetailRepository postDetailRepository;
    private final ReservationRepository reservationRepository;
    private final RegionRepository regionRepository;
    private final BusinessRepository businessRepository;
    private final LikesRepository likesRepository;
    private final FileUtils fileUtils;
    private final FileRepository fileRepository;

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


    public ResponsePostDetailDto getPostDetail(Long userId, Long postId, LocalDate startDate) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));
        PostDetail postDetail = postDetailRepository.findByPostIdAndStartDate(postId, startDate)
                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));
        Region region = regionRepository.findById(post.getRegionId())
                .orElseThrow(() -> new CustomException("존재하지 않는 지역입니다."));
        Business business = businessRepository.findByUserId(post.getUserId())
                .orElseThrow(() -> new CustomException("존재하지 않는 사업자입니다."));
        List<File> fileList = fileRepository.findByPostId(post.getId());

        List<FileResponse> imgResponse = new ArrayList<FileResponse>();
        for(File img : fileList){
            FileResponse fileResponse = new FileResponse();
            fileResponse.setFileId(img.getId());
            fileResponse.setImgSrc(serverUrl + fileSrc + img.getStoredFileName());

            imgResponse.add(fileResponse);
        }


        boolean likesTF = false;
        if(userId != null) likesTF = likesRepository.findByUserIdAndPostDetailId(userId, postDetail.getId()).isPresent();


        Status status = Status.CLOSED;

        if(post.getStatus() == Status.OPEN && postDetail.getStatus() == Status.OPEN && postDetail.getAvailableCnt() > 0) status = Status.OPEN;

        return ResponsePostDetailDto.builder()
                .postDetailId(postDetail.getId())
                .title(post.getTitle())
                .imgResponse(imgResponse)
                .country(region.getCountry())
                .city(region.getCity())
                .tradeName(business.getTradeName())
                .businessMobile(business.getBusinessMobile())
                .businessEmail(business.getBusinessEmail())
                .description(post.getDescription())
                .startDate(postDetail.getStartDate())
                .pricePerTeam(postDetail.getPricePerTeam())
                .minPeople(post.getMinPeople())
                .maxPeople(post.getMaxPeople())
                .availableCnt(postDetail.getAvailableCnt())
                .likesTF(likesTF)
                .status(status)
                .build();
    }

    @Transactional
    public void createPost(CreatePostDto createPostDto) {
        PostDto postDto = createPostDto.getPostDto();
        postDto.setStatus(Status.OPEN);     // post의 초기상태 = OPEN
        regionRepository.findById(postDto.getRegionId()).orElseThrow(() -> new CustomException("존재하지 않는 지역입니다."));
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
    public void updatePost(UpdatePostDto updatePostDto, MultipartFile[] files) {
        Post post = postRepository.findById(updatePostDto.getPostId())
                .orElseThrow(() -> new CustomException("없는 게시물입니다."));
        if(post.getUserId() != updatePostDto.getUserId()) throw new CustomException("회원정보가 일치하지 않습니다.");
        if(updatePostDto.getRegionId() != null){
            regionRepository.findById(updatePostDto.getRegionId())
                    .orElseThrow(() -> new CustomException("없는 지역입니다."));
        }

        // 삭제할 이미지 id 값을 받아서, db에서 삭제 및 폴더에서 사진 삭제
        if(updatePostDto.getDeleteImgIdList() != null && !updatePostDto.getDeleteImgIdList().isEmpty()){
            for (Long fileId : updatePostDto.getDeleteImgIdList()) {
                File file = fileRepository.findById(fileId).orElseThrow(() -> new CustomException("없는 파일은 삭제할 수 없습니다."));
                fileUtils.deleteFile(file);
                fileRepository.delete(file);
            }
        }

        // 추가한 이미지 파일이 있으면, db 추가 및 폴더에 사진 업로드
        if(files.length > 0){
            for(MultipartFile multipartFile : files){
                File file = fileUtils.uploadFile(multipartFile, post.getId());
                fileRepository.save(file);
            }
        }

        post.change(updatePostDto);
    }

    @Transactional
    public void deletePostDetail(Long postId, LocalDate startDate, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));
//        if(post.isDeleted()) throw new CustomException("게시글이 존재하지 않습니다.");
        if(post.getUserId() != userId) throw new CustomException("회원정보가 일치하지 않습니다.");
        PostDetail postDetail = postDetailRepository.findByPostIdAndStartDate(postId, startDate)
                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));
        postDetail.changeStatus(Status.CLOSED);
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

    @Transactional
    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException("없는 게시물입니다."));
        if(post.getUserId() != userId) throw new CustomException("회원정보가 일치하지 않습니다.");
        List<Reservation> reservationList = reservationRepository.findReservationByPostIdAndReserved(post.getId());

        if(!reservationList.isEmpty()) throw new CustomException("예약건이 있습니다. 완료 이후 삭제가 가능합니다.");
        post.deleted();
//        PostDetail postDetail = postDetailRepository.findByPostIdAndStartDate(postDetailDto.getPostId(), postDetailDto.getStartDate())
//                .orElseThrow(() -> new CustomException("게시글이 존재하지 않습니다."));

    }

    @Transactional
    public void createPostWithImages(CreatePostDto createPostDto, MultipartFile[] files) {
        PostDto postDto = createPostDto.getPostDto();
        postDto.setStatus(Status.OPEN);     // post의 초기상태 = OPEN
        regionRepository.findById(postDto.getRegionId()).orElseThrow(() -> new CustomException("존재하지 않는 지역입니다."));

        List<PostDetailDto> postDetailDtoList = createPostDto.getPostDetailDtoList();
        Post post = PostMapper.toPostEntity(postDto);
        postRepository.save(post);

        // 상세 게시글 등록
        for(PostDetailDto postDetailDto : postDetailDtoList){
            postDetailDto.setPostId(post.getId());
            postDetailDto.setStatus(Status.OPEN);
            postDetailDto.setAvailableCnt(postDetailDto.getTotalCnt());     // 초기에는 [예약 가능한 개수 = 총 개수]
            PostDetail postDetail = PostDetailMapper.ToPostDetailEntity(postDetailDto);
            postDetailRepository.save(postDetail);
        }

        // 게시글의 사진 업로드
        for(MultipartFile multipartFile : files){
            File file = fileUtils.uploadFile(multipartFile, post.getId());
            fileRepository.save(file);
        }
    }
}
