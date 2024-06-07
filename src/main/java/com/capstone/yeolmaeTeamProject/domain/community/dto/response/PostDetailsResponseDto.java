package com.capstone.yeolmaeTeamProject.domain.community.dto.response;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostDetailsResponseDto {

    private Long id;

    private String writerName;

    private String parentCategory;

    private String category;

    private String title;

    private String content;

    private String imageUrl;

    private List<CommentResponseDto> comments;

    private LocalDateTime createdAt;

    public static PostDetailsResponseDto toDto(Post post, List<CommentResponseDto> comments) {
        return PostDetailsResponseDto.builder()
                .id(post.getId())
                .writerName(post.getWriterName())
                .parentCategory(post.getParentCategory())
                .category(post.getCategory())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .comments(comments)
                .createdAt(post.getCreatedAt())
                .build();
    }

}
