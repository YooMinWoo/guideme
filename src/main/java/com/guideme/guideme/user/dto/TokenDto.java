package com.guideme.guideme.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TokenDto {
    private final String accessToken;
    private final String refreshToken;
}
