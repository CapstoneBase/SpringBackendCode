package com.capstone.yeolmaeTeamProject.domain.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostUpdateRequestDto {

    @Schema(description = "부모 카테고리", example = "공지사항")
    private String parentCategory;

    @Schema(description = "카테고리", example = "공지사항")
    private String category;

    @Schema(description = "제목", example = "제목")
    private String title;

    @Schema(description = "내용", example = "내용")
    private String content;

    @Schema(description = "이미지 URL", example = "https://image.com")
    private String imageUrl;

    @Schema(description = "첨부파일 경로 리스트", example = "[\"/resources/files/boards/339609571877700_4305d83e-090a-480b-a470-b5e96164d113.png\", \"/resources/files/boards/4305d83e-090a-480b-a470-b5e96164d114.png\"]")
    private List<String> fileUrlList;
}
