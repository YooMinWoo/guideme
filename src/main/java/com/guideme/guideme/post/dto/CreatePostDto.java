package com.guideme.guideme.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDto {

    private PostDto postDto;
    private List<SeasonalPriceDto> seasonalPriceDto;
    private List<WeekdayPriceDto> weekdayPriceDto;

//    private List<PostDetailDto> postDetailDto;
}
