package com.capstone.yeolmaeTeamProject.domain.community.application;

import com.capstone.yeolmaeTeamProject.domain.community.dao.CommentRepository;
import com.capstone.yeolmaeTeamProject.domain.community.domain.Comment;
import com.capstone.yeolmaeTeamProject.domain.community.dto.request.CommentRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.application.UserService;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.exception.NotFoundException;
import com.capstone.yeolmaeTeamProject.global.validation.ValidationService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserService userService;

    private final PostService postService;

    private final ValidationService validationService;

    private final CommentRepository commentRepository;

    public Long createComment(CommentRequestDto requestDto) {
        User currentUser = userService.getCurruentUser();
        validateCommentForCreation(requestDto);
        Comment comment = CommentRequestDto.toDto(requestDto, currentUser);
        validationService.checkValid(comment);
        return commentRepository.save(comment).getId();
    }

    private void validateCommentForCreation(CommentRequestDto requestDto) {
        postService.getPostByIdOrThrow(requestDto.getPostId());
        if (requestDto.getParentId() != null) {
            Comment parentComment = commentRepository.findById(requestDto.getParentId())
                    .orElseThrow(() -> new NotFoundException("부모 댓글을 찾을 수 없습니다."));
            if (!parentComment.isSamePostComment(requestDto.getPostId())) {
                throw new IllegalArgumentException("부모 댓글의 postId와 요청의 postId가 일치하지 않습니다.");
            }
        }
    }
}
