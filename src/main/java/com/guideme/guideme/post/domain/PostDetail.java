package com.guideme.guideme.post.domain;

import com.guideme.guideme.post.dto.PostDetailDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetail {

    @Id
    @GeneratedValue
    @Column(name = "post_detail_id")
    private Long id;

    private Long postId;

    private LocalDate startDate;
    private int pricePerTeam;   // 날짜별 한 팀 당 가격

    private int cnt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public PostDetail(Long postId, LocalDate startDate, int pricePerTeam, int cnt, Status status) {
        this.postId = postId;
        this.startDate = startDate;
        this.pricePerTeam = pricePerTeam;
        this.cnt = cnt;
        this.status = status;
    }

    public void change(PostDetailDto postDetailDto){
        if(postDetailDto.getPricePerTeam() != 0) this.pricePerTeam = postDetailDto.getPricePerTeam();
        if(postDetailDto.getCnt() != null) this.cnt = postDetailDto.getCnt();
        if(postDetailDto.getStatus() != null) this.status = postDetailDto.getStatus();
//        this.status = postDetailDto.getStatus();
    }

    public void changePricePerTeam(int pricePerTeam){
        this.pricePerTeam = pricePerTeam;
    }

    public void changeStatus(Status status){
        this.status = status;
    }
}
