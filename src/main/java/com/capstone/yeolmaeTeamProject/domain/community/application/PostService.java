package com.capstone.yeolmaeTeamProject.domain.community.application;

import com.capstone.yeolmaeTeamProject.domain.community.dao.PostRepository;
import com.capstone.yeolmaeTeamProject.domain.community.domain.Post;
import com.capstone.yeolmaeTeamProject.domain.community.dto.request.PostRequestDto;
import com.capstone.yeolmaeTeamProject.domain.community.dto.request.PostUpdateRequestDto;
import com.capstone.yeolmaeTeamProject.domain.community.dto.response.PostDetailsResponseDto;
import com.capstone.yeolmaeTeamProject.domain.community.dto.response.PostResponseDto;
import com.capstone.yeolmaeTeamProject.domain.user.application.UserService;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;

import com.capstone.yeolmaeTeamProject.global.common.dto.PagedResponseDto;
import com.capstone.yeolmaeTeamProject.global.exception.NotFoundException;
import com.capstone.yeolmaeTeamProject.global.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserService userService;

    private final ValidationService validationService;

    private final PostRepository postRepository;

    public Long createPost(PostRequestDto requestDto) {
        User writer = userService.getCurruentUser();
        Post post = PostRequestDto.toEntity(requestDto, writer);
        validationService.checkValid(post);
        return postRepository.save(post).getId();
    }

    public Long updatePost(Long postId, PostUpdateRequestDto requestDto) {
        User curruentUser = userService.getCurruentUser();
        Post post = getPostByIdOrThrow(postId);
        post.validateAccessPermission(curruentUser);
        post.update(requestDto);
        validationService.checkValid(post);
        postRepository.save(post);
        return post.getId();
    }

    public Long deletePost(Long postId) {
        User curruentUser = userService.getCurruentUser();
        Post post = getPostByIdOrThrow(postId);
        post.validateAccessPermission(curruentUser);
        postRepository.delete(post);
        return post.getId();
    }

    public Post getPostByIdOrThrow(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("해당 게시글이 존재하지 않습니다."));
    }

    public PagedResponseDto<PostResponseDto> getPostsByCategory(String parentCategory, String category, Pageable pageable) {
        Page<Post> posts = postRepository.findAllByParentCategoryAndCategory(parentCategory, category, pageable);
        return new PagedResponseDto<>(posts.map(PostResponseDto::toDto));
    }

    public PostDetailsResponseDto getPostDetails(Long postId) {
        Post post = getPostByIdOrThrow(postId);
        return PostDetailsResponseDto.toDto(post);
    }

}
