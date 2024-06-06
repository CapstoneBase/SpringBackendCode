package com.capstone.yeolmaeTeamProject.domain.community.domain;

import com.capstone.yeolmaeTeamProject.domain.community.dto.request.CommentUpdateRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.exception.PermissionDeniedException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Optional;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private Long parentId;

    private String writerId;

    private String writerName;

    private String content;

    public void update(CommentUpdateRequestDto requestDto) {
        Optional.ofNullable(requestDto.getContent()).ifPresent(this::setContent);
    }

    public boolean isOwner(User currentUser) {
        return this.writerId.equals(currentUser.getId());
    }

    public void validateAccessPermission(User currentUser) {
        if (!isOwner(currentUser) && !currentUser.isAdmin()) {
            throw new PermissionDeniedException("댓글 수정/삭제 권한이 없습니다.");
        }
    }

    public boolean isSamePostComment(Long postId) {
        return this.postId.equals(postId);
    }

}
