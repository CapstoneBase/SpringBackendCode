package com.capstone.yeolmaeTeamProject.domain.api;

import com.capstone.yeolmaeTeamProject.domain.application.UserService;
import com.capstone.yeolmaeTeamProject.domain.dto.request.UserRequestDto;
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
}
