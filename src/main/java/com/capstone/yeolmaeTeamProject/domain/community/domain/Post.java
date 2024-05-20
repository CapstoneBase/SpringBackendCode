package com.capstone.yeolmaeTeamProject.domain.community.domain;

import com.capstone.yeolmaeTeamProject.domain.community.dto.request.PostUpdateRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.common.domain.BaseEntity;
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
@Table(indexes = {
        @Index(name = "idx_category", columnList = "category"),
        @Index(name = "idx_parentCategory", columnList = "parentCategory")
})
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    private String parentCategory;

    @Column(nullable = false)
    @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하로 입력해주세요.")
    private String title;

    @Column(nullable = false)
    @Size(min = 1, max = 10000, message = "내용은 1자 이상 1000자 이하로 입력해주세요.")
    private String content;

    @ManyToOne
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    private String imageUrl;

    public void update(PostUpdateRequestDto requestDto) {
        Optional.ofNullable(requestDto.getCategory()).ifPresent(this::setCategory);
        Optional.ofNullable(requestDto.getParentCategory()).ifPresent(this::setParentCategory);
        Optional.ofNullable(requestDto.getTitle()).ifPresent(this::setTitle);
        Optional.ofNullable(requestDto.getContent()).ifPresent(this::setContent);
        Optional.ofNullable(requestDto.getImageUrl()).ifPresent(this::setImageUrl);
    }

    public boolean isOwner(User user) {
        return writer.equals(user);
    }

    public void validateAccessPermission(User user) {
        if (!isOwner(user) && !user.isAdmin()) {
            throw new PermissionDeniedException("게시글 수정/삭제 권한이 없습니다.");
        }
    }
}
