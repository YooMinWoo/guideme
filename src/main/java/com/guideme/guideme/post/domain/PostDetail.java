package com.guideme.guideme.post.domain;

import com.guideme.guideme.global.exception.CustomException;
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

    private int totalCnt;
    private int availableCnt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public PostDetail(Long postId, LocalDate startDate, int pricePerTeam, int totalCnt, int availableCnt, Status status) {
        this.postId = postId;
        this.startDate = startDate;
        this.pricePerTeam = pricePerTeam;
        this.totalCnt = totalCnt;
        this.availableCnt = availableCnt;
        this.status = status;
    }



    public void change(PostDetailDto postDetailDto){
        if(postDetailDto.getPricePerTeam() != 0) this.pricePerTeam = postDetailDto.getPricePerTeam();
        if(postDetailDto.getTotalCnt() != null) {
            int changeCnt = postDetailDto.getTotalCnt() - this.totalCnt;        // 5 -> 3 = -2 , 3 -> 5 = 2
            this.totalCnt += changeCnt;
            this.availableCnt += changeCnt;
            if(this.availableCnt < 0) throw new CustomException("현재 예약 가능한 수보다 적게 설정할 수 없습니다.");
        }
        if(postDetailDto.getStatus() != null) this.status = postDetailDto.getStatus();
    }

    public void changePricePerTeam(int pricePerTeam){
        this.pricePerTeam = pricePerTeam;
    }

    public void changeStatus(Status status){
        this.status = status;
    }

    public void changeAvailableCnt(){
        this.availableCnt -= 1;
    }
}
