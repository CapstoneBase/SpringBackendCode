package com.capstone.yeolmaeTeamProject.domain.community.api;

import com.capstone.yeolmaeTeamProject.domain.community.application.PostService;
import com.capstone.yeolmaeTeamProject.domain.community.dto.request.PostRequestDto;
import com.capstone.yeolmaeTeamProject.global.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Post", description = "게시글")
public class PostController {

    private final PostService postService;

    @Operation(summary = "[U] 게시글 생성", description = "ROLE_USER 이상의 권한이 필요함")
    @PostMapping("")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ApiResponse<Long> createPost(
            @Valid @RequestBody PostRequestDto requestDto
    ) {
        Long id = postService.createPost(requestDto);
        return ApiResponse.success(id);
    }
}
