package com.capstone.yeolmaeTeamProject.domain.community.api;

import com.capstone.yeolmaeTeamProject.domain.community.application.PostService;
import com.capstone.yeolmaeTeamProject.domain.community.dto.request.PostRequestDto;
import com.capstone.yeolmaeTeamProject.domain.community.dto.request.PostUpdateRequestDto;
import com.capstone.yeolmaeTeamProject.global.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "[U] 게시글 수정", description = "ROLE_USER 이상의 권한이 필요함")
    @PatchMapping("/{postId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ApiResponse<Long> updatePost(
            @PathVariable("postId") Long postId,
            @Valid @RequestBody PostUpdateRequestDto requestDto
    ) {
        Long id = postService.updatePost(postId, requestDto);
        return ApiResponse.success(id);
    }

    @Operation(summary = "[U] 게시글 삭제", description = "ROLE_USER 이상의 권한이 필요함")
    @DeleteMapping("/{postId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ApiResponse<Long> deletePost(
            @PathVariable("postId") Long postId
    ) {
        Long id = postService.deletePost(postId);
        return ApiResponse.success(id);
    }
}
