package com.capstone.yeolmaeTeamProject.domain.login.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotNull(message = "아이디를 입력해주세요.")
    @Schema(description = "아이디", example = "yeolmae", required = true)
    private String id;

    @NotNull(message = "비밀번호를 입력해주세요.")
    @Schema(description = "비밀번호", example = "1234", required = true)
    private String password;

}
