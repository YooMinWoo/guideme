package com.guideme.guideme.post.mapper;

import com.guideme.guideme.post.domain.Post;
import com.guideme.guideme.post.domain.SeasonalPrice;
import com.guideme.guideme.post.domain.WeekdayPrice;
import com.guideme.guideme.post.dto.PostDto;
import com.guideme.guideme.post.dto.SeasonalPriceDto;
import com.guideme.guideme.post.dto.WeekdayPriceDto;

public class PostMapper {

    public static Post toPostEntity(PostDto postDto){

        return Post.builder()
                .userId(postDto.getUserId())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .price(postDto.getPrice())
                .status(postDto.getStatus())
                .build();
    }

    public static PostDto toPostDto(Post post) {
        return PostDto.builder()
                .postId(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .description(post.getDescription())
                .status(post.getStatus())
                .build();
    }

    public static SeasonalPrice toSeasonalPriceEntity(SeasonalPriceDto seasonalPriceDto){
        return SeasonalPrice.builder()
                .postId(seasonalPriceDto.getPostId())
                .date(seasonalPriceDto.getDate())
                .price(seasonalPriceDto.getPrice())
                .build();
    }

    public static WeekdayPrice toWeekdayPriceEntity(WeekdayPriceDto weekdayPriceDto){
        return WeekdayPrice.builder()
                .postId(weekdayPriceDto.getPostId())
                .weekday(weekdayPriceDto.getWeekday())
                .price(weekdayPriceDto.getPrice())
                .build();
    }

}
