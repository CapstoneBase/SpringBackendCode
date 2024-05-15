package com.capstone.yeolmaeTeamProject.domain.user.api;

import com.capstone.yeolmaeTeamProject.domain.user.application.UserService;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserIdRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserRequestDto;
import com.capstone.yeolmaeTeamProject.global.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 생성")
    @PostMapping("")
    public ApiResponse<String> createUser(
            @Valid @RequestBody UserRequestDto requestDto
    ) {
        String id = userService.createUser(requestDto);
        return ApiResponse.success(id);
    }

    @Operation(summary = "아이디 중복 확인", description = "이미 존재하는 아이디일 경우 true 반환")
    @PostMapping("/check")
    public ApiResponse<Boolean> checkId(
            @Valid @RequestBody UserIdRequestDto requestDto
    ) {
        Boolean result = userService.checkId(requestDto);
        return ApiResponse.success(result);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("")
    public ApiResponse<String> deleteUser() {
        String id = userService.deleteUser();
        return ApiResponse.success(id);
    }
}
