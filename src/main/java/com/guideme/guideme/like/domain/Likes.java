package com.guideme.guideme.like.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes {

    @Id
    @GeneratedValue
    @Column(name = "likes_id")
    private Long id;

    private Long userId;
    private Long postDetailId;

    public Likes(Long userId, Long postDetailId) {
        this.userId = userId;
        this.postDetailId = postDetailId;
    }
}
