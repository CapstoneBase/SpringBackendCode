package com.capstone.yeolmaeTeamProject.domain.login.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenInfo {

    private String accessToken;

    private String refreshToken;

    public static TokenInfo create(String accessToken, String refreshToken) {
        return TokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
