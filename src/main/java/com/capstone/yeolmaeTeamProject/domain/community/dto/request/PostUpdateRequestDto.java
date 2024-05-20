package com.capstone.yeolmaeTeamProject.domain.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequestDto {

    @Schema(description = "카테고리", example = "공지사항")
    private String category;

    @Schema(description = "부모 카테고리", example = "공지사항")
    private String parentCategory;

    @Schema(description = "제목", example = "제목")
    private String title;

    @Schema(description = "내용", example = "내용")
    private String content;

    @Schema(description = "이미지 URL", example = "https://image.com")
    private String imageUrl;
}
