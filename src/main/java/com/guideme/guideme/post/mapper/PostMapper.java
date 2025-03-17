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
                .user_id(postDto.getUser_id())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .defaultPrice(postDto.getDefaultPrice())
                .status(postDto.getStatus())
                .build();
    }

    public static PostDto toPostDto(Post post) {
        return PostDto.builder()
                .post_id(post.getId())
                .user_id(post.getUser_id())
                .title(post.getTitle())
                .description(post.getDescription())
                .defaultPrice(post.getDefaultPrice())
                .status(post.getStatus())
                .build();
    }

    public static SeasonalPrice toSeasonalPriceEntity(SeasonalPriceDto seasonalPriceDto){
        return SeasonalPrice.builder()
                .post_id(seasonalPriceDto.getPost_id())
                .date(seasonalPriceDto.getDate())
                .price(seasonalPriceDto.getPrice())
                .build();
    }

    public static WeekdayPrice toWeekdayPriceEntity(WeekdayPriceDto weekdayPriceDto){
        return WeekdayPrice.builder()
                .post_id(weekdayPriceDto.getPost_id())
                .weekday(weekdayPriceDto.getWeekday())
                .price(weekdayPriceDto.getPrice())
                .build();
    }

}
