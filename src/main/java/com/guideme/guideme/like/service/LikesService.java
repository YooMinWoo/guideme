package com.guideme.guideme.like.service;

import com.guideme.guideme.like.domain.Likes;
import com.guideme.guideme.like.dto.LikesDto;
import com.guideme.guideme.like.mapper.LikesMapper;
import com.guideme.guideme.like.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;

//    public int likesCnt(Long postId){
//        List<Likes> likesList = likesRepository.findAllByPostId(postId);
//        return likesList.size();
//    }

    public List<LikesDto> likesList(Long userId){
        List<Likes> likesList = likesRepository.findAllByUserId(userId);
        return LikesMapper.toLikesDto(likesList);
    }

    @Transactional
    public void likes(Long userId, Long postDetailId) {
        likesRepository.findByUserIdAndPostDetailId(userId, postDetailId)
                .ifPresentOrElse(
                        likesRepository::delete,
                        () -> likesRepository.save(new Likes(userId, postDetailId))
                );
    }

}
