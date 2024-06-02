package com.capstone.yeolmaeTeamProject.domain.user.api;

import com.capstone.yeolmaeTeamProject.domain.user.application.UserService;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserIdRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserUpdateRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.dto.response.UserResponseDto;
import com.capstone.yeolmaeTeamProject.global.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "회원")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 생성")
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

    @Operation(summary = "[U] 회원 정보 수정", description = "ROLE_USER 권한이 필요한 상태")
    @PatchMapping("")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})   //권한 체크 기능 추가
    public ApiResponse<String> updateUser(
            @Valid @RequestBody UserUpdateRequestDto requestDto
    ) {
        String id = userService.updateUser(requestDto);
        return ApiResponse.success(id);
    }

    @Operation(summary = "[U] 나의 프로필 조회", description = "ROLE_USER 권한이 필요함")
    @GetMapping("")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})   //권한 체크 기능 추가
    public ApiResponse<UserResponseDto> getProfile() {
        UserResponseDto myProfile = userService.getMyProfile();
        return ApiResponse.success(myProfile);
    }

    @Operation(summary = "회원 프로필 조회", description = "로그인(권한) 하지 않아도 조회 가능")
    @GetMapping("/{userId}")
//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ApiResponse<UserResponseDto> getUserProfile(
            @PathVariable("userId") String userId
    ) {
        UserResponseDto user = userService.getUserProfile(userId);
        return ApiResponse.success(user);
    }

    @Operation(summary = "회원 탈퇴", description = "ROLE_USER 권한이 필요한 상태")
    @DeleteMapping("")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})   //권한 체크 기능 추가
    public ApiResponse<String> deleteUser() {
        String id = userService.deleteUser();
        return ApiResponse.success(id);
    }
}
