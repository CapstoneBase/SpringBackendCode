package com.capstone.yeolmaeTeamProject.domain.community.domain;

import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.common.domain.BaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.*;

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
}
