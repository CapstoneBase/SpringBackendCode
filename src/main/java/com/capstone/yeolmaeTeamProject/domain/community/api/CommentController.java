package com.capstone.yeolmaeTeamProject.domain.community.api;

import com.capstone.yeolmaeTeamProject.domain.community.application.CommentService;
import com.capstone.yeolmaeTeamProject.domain.community.dto.request.CommentRequestDto;
import com.capstone.yeolmaeTeamProject.domain.community.dto.request.CommentUpdateRequestDto;
import com.capstone.yeolmaeTeamProject.global.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "댓글")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "[U] 댓글 생성", description = "ROLE_USER 이상의 권한이 필요함")
    @PostMapping("")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ApiResponse<Long> createComment(
            @Valid @RequestBody CommentRequestDto requestDto
    ) {
        Long id = commentService.createComment(requestDto);
        return ApiResponse.success(id);
    }

    @Operation(summary = "[U] 댓글 수정", description = "ROLE_USER 이상의 권한이 필요함")
    @PatchMapping("/{commentId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ApiResponse<Long> updateComment(
            @PathVariable("commentId") Long commentId,
            @Valid @RequestBody CommentUpdateRequestDto requestDto
    ) {
        Long id = commentService.updateComment(commentId, requestDto);
        return ApiResponse.success(id);
    }

    @Operation(summary = "[U] 댓글 삭제", description = "ROLE_USER 이상의 권한이 필요함")
    @DeleteMapping("/{commentId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ApiResponse<Long> deleteComment(
            @PathVariable("commentId") Long commentId
    ) {
        Long id = commentService.deleteComment(commentId);
        return ApiResponse.success(id);
    }
}

