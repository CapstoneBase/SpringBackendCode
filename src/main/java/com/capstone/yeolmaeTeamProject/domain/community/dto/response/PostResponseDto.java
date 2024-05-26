package com.capstone.yeolmaeTeamProject.domain.community.dto.response;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponseDto {

    private Long id;

    private String writerName;

    private String parentCategory;

    private String category;

    private String title;

    private String imageUrl;

    private LocalDateTime createdAt;

    public static PostResponseDto toDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .writerName(post.getWriterName())
                .parentCategory(post.getParentCategory())
                .category(post.getCategory())
                .title(post.getTitle())
                .imageUrl(post.getImageUrl())
                .createdAt(post.getCreatedAt())
                .build();
    }

}
