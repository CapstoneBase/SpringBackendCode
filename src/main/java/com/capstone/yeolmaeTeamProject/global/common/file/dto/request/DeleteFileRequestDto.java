package com.capstone.yeolmaeTeamProject.global.common.file.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeleteFileRequestDto {

    @NotNull(message = "{notNull.file.url}")
    @Schema(description = "파일 경로", example = "/resources/files/forms/123456.png", required = true)
    private String url;

    private DeleteFileRequestDto() {
    }

    private DeleteFileRequestDto(String url) {
        this.url = url;
    }

    public static DeleteFileRequestDto create(String url) {
        return DeleteFileRequestDto.builder()
                .url(url)
                .build();
    }

}
