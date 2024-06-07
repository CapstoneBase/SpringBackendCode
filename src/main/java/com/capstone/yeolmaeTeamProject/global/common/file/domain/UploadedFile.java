package com.capstone.yeolmaeTeamProject.global.common.file.domain;

import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.common.domain.BaseEntity;
import com.capstone.yeolmaeTeamProject.global.exception.PermissionDeniedException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadedFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User uploader;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false, unique = true)
    private String saveFileName;

    @Column(nullable = false, unique = true)
    private String savedPath;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private String contentType;

    public static UploadedFile create(User uploader, String originalFileName, String saveFileName, String savedPath, String url, Long fileSize, String contentType) {
        return UploadedFile.builder()
                .uploader(uploader)
                .originalFileName(originalFileName)
                .saveFileName(saveFileName)
                .savedPath(savedPath)
                .url(url)
                .fileSize(fileSize)
                .contentType(contentType)
                .build();
    }

    public boolean isOwner(User member) {
        return this.uploader.isSameUser(member);
    }

    public void validateAccessPermission(User user) throws PermissionDeniedException {
        if (!isOwner(user) && !user.isAdmin()) {
            throw new PermissionDeniedException("해당 파일을 삭제할 권한이 없습니다.");
        }
    }
}
