package com.capstone.yeolmaeTeamProject.domain.community.dto.response;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CommentResponseDto {

    private Long id;

    private String writerId;

    private String writerName;

    private String content;

    private List<CommentResponseDto> children;

    private LocalDateTime createdAt;

    public static CommentResponseDto toDto(Comment comment, boolean includeDeleted) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .writerId(comment.getIsDeleted() && includeDeleted ? null : comment.getWriterId())
                .writerName(comment.getIsDeleted() && includeDeleted ? null : comment.getWriterName())
                .content(comment.getIsDeleted() && includeDeleted ? "삭제된 댓글입니다." : comment.getContent())
                .children(new ArrayList<>())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
