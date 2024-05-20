package com.capstone.yeolmaeTeamProject.domain.community.dto.request;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Post;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {

    @NotNull(message = "카테고리를 입력해주세요.")
    @Schema(description = "카테고리", example = "공지사항")
    private String category;

    //소분류쪽 확정되면 필수 권한 추가
//    @NotNull(message = "카테고리를 입력해주세요.")
    @Schema(description = "부모 카테고리", example = "공지사항")
    private String parentCategory;

    @NotNull(message = "제목을 입력해주세요.")
    @Schema(description = "제목", example = "제목")
    private String title;

    @NotNull(message = "내용을 입력해주세요.")
    @Schema(description = "내용", example = "내용")
    private String content;

    @Schema(description = "이미지 URL", example = "https://image.com")
    private String imageUrl;

    public static Post toEntity(PostRequestDto requestDto, User writer) {
        return Post.builder()
                .category(requestDto.getCategory())
                .parentCategory(requestDto.getParentCategory())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .writer(writer)
                .imageUrl(requestDto.getImageUrl())
                .build();
    }
}
