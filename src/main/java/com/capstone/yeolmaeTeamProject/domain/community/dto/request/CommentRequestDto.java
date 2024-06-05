package com.capstone.yeolmaeTeamProject.domain.community.dto.request;

import com.capstone.yeolmaeTeamProject.domain.community.domain.Comment;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    private Long postId;

    private Long parentId;

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
