package com.capstone.yeolmaeTeamProject.domain.login.api;

import com.capstone.yeolmaeTeamProject.domain.login.application.LoginService;
import com.capstone.yeolmaeTeamProject.domain.login.dto.request.LoginRequestDto;
import com.capstone.yeolmaeTeamProject.global.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
@Tag(name = "Login", description = "로그인")
public class LoginController {

    private final LoginService loginService;

    @Operation(summary = "로그인")
    @PostMapping("")
    public ApiResponse<String> login(
            @Valid @RequestBody LoginRequestDto requestDto
    ) {
        String id = loginService.login(requestDto);
        return ApiResponse.success(id);
    }

}
