package com.guideme.guideme.post.domain;

import com.guideme.guideme.global.entity.BaseEntity;
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

    private int price;   // 기본 가격

    @Enumerated(EnumType.STRING)
    private Status status;    // 상태

//    private int adultPrice;  // 대인 요금
//    private int childPrice;  // 소인 요금
//    private LocalDate postDate; // 날짜

//    public void changePost(PostDto postDto){
//        this.title = postDto.getTitle();
//        this.title = postDto.getTitle();
//        this.title = postDto.getTitle();
//        this.title = postDto.getTitle();
//        this.title = postDto.getTitle();
//    }

    @Builder
    public Post(Long userId, String title, String description, int price, Status status) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.status = status;
    }
}

