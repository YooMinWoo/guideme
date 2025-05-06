package com.guideme.guideme.post.domain;

import com.guideme.guideme.global.entity.BaseEntity;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.post.dto.UpdatePostDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted = false")
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private Long userId;
    private Long regionId;      // 지역 도메인

    private String title;   // 제목
    private String description; // 설명

    private int minPeople;      // 최소 인원
    private int maxPeople;      // 최대 인원

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean deleted = false;     // 삭제여부

    @Builder
    public Post(Long userId, Long regionId, String title, String description, int minPeople, int maxPeople, Status status) {
        this.userId = userId;
        this.regionId = regionId;
        this.title = title;
        this.description = description;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
        this.status = status;
    }



    public void change(UpdatePostDto updatePostDto) {
        if(updatePostDto.getTitle() != null && !updatePostDto.getTitle().isEmpty()) {
            System.out.println("title : " + title + " \t -> " + updatePostDto.getTitle());
            title = updatePostDto.getTitle();
        }

        if(updatePostDto.getDescription() != null && !updatePostDto.getDescription().isEmpty()) description = updatePostDto.getDescription();
        if(updatePostDto.getMinPeople() != 0) minPeople = updatePostDto.getMinPeople();
        if(updatePostDto.getMaxPeople() != 0) maxPeople = updatePostDto.getMaxPeople();
        if(updatePostDto.getStatus() != null) status = updatePostDto.getStatus();
        if(updatePostDto.getRegionId() != null) regionId = updatePostDto.getRegionId();
    }

    public void deleted(){
        this.deleted = true;
    }
}

