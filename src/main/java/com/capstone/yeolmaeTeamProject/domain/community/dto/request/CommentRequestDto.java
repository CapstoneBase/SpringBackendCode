package com.capstone.yeolmaeTeamProject.domain.community.dto.request;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Comment;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    @NotNull
    private Long postId;

    private Long parentId;

    @NotNull
    private String content;

    public static Comment toDto(CommentRequestDto requestDto, User writer) {
        return Comment.builder()
                .postId(requestDto.getPostId())
                .parentId(requestDto.getParentId())
                .writerId(writer.getId())
                .writerName(writer.getName())
                .content(requestDto.getContent())
                .build();
    }

}
