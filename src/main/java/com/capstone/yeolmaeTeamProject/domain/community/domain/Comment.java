package com.capstone.yeolmaeTeamProject.domain.community.domain;

import com.capstone.yeolmaeTeamProject.domain.community.dto.request.CommentUpdateRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.exception.PermissionDeniedException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(nullable = false)
    private Long postId;

    private Long parentId;

    @Column(nullable = false)
    private String writerId;

    @Column(nullable = false)
    private String writerName;

    @Column(nullable = false)
    @Size(min = 1, max = 1000, message = "댓글은 1자 이상 1000자 이하로 작성해야 합니다.")
    private String content;

    @Column(name = "is_deleted")
    protected Boolean isDeleted = Boolean.FALSE;

    public void update(CommentUpdateRequestDto requestDto) {
        Optional.ofNullable(requestDto.getContent()).ifPresent(this::setContent);
    }

    public void delete(){
        this.isDeleted = true;
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
