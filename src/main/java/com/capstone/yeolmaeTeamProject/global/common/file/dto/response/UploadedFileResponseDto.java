package com.capstone.yeolmaeTeamProject.global.common.file.dto.response;

import com.capstone.yeolmaeTeamProject.global.common.file.domain.UploadedFile;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UploadedFileResponseDto {

    private String fileUrl;

    private String originalFileName;

    private LocalDateTime createdAt;

    public static List<UploadedFileResponseDto> toDto(List<UploadedFile> uploadedFiles) {
        return uploadedFiles.stream()
                .map(UploadedFileResponseDto::toDto)
                .toList();
    }

    public static UploadedFileResponseDto toDto(UploadedFile uploadedFile) {
        return UploadedFileResponseDto.builder()
                .fileUrl(uploadedFile.getUrl())
                .originalFileName(uploadedFile.getOriginalFileName())
                .createdAt(uploadedFile.getCreatedAt())
                .build();
    }
}
