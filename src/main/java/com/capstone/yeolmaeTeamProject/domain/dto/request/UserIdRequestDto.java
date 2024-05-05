package com.capstone.yeolmaeTeamProject.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdRequestDto {

    @NotNull(message = "아이디를 입력해주세요.")
    @Schema(description = "아이디", example = "yeolmae")
    private String id;

}
