package com.guideme.guideme.region.domain;

import com.guideme.guideme.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "region", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"country", "city"})
})
public class Region extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "region_id")
    private Long id;

    private Long userId;

    private String country;  // "일본", "한국"
    private String city;     // "도쿄", "후쿠오카", "서울"

    @Builder
    public Region(Long userId, String country, String city) {
        this.userId = userId;
        this.country = country;
        this.city = city;
    }
}
