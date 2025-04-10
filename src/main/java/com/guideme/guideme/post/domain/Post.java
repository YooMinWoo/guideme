package com.guideme.guideme.post.domain;

import com.guideme.guideme.global.entity.BaseEntity;
import com.guideme.guideme.post.dto.PostDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private Long userId;

    private String title;   // 제목
    private String description; // 설명

    private int minPeople;      // 최소 인원
    private int maxPeople;      // 최대 인원


    @Builder
    public Post(Long userId, String title, String description, int minPeople, int maxPeople) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.minPeople = minPeople;
        this.maxPeople = maxPeople;
    }


    public void change(PostDto postDto) {
        if(postDto.getTitle() != null && !postDto.getTitle().isEmpty()) {
            System.out.println("title : " + title + " \t -> " + postDto.getTitle());
            title = postDto.getTitle();
        }
        if(postDto.getDescription() != null && !postDto.getDescription().isEmpty()) description = postDto.getDescription();
        if(postDto.getMinPeople() != 0) minPeople = postDto.getMinPeople();
        if(postDto.getMaxPeople() != 0) maxPeople = postDto.getMaxPeople();
    }
}

