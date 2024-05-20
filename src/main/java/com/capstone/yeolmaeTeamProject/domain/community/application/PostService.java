package com.capstone.yeolmaeTeamProject.domain.community.application;

import com.capstone.yeolmaeTeamProject.domain.community.dao.PostRepository;
import com.capstone.yeolmaeTeamProject.domain.community.domain.Post;
import com.capstone.yeolmaeTeamProject.domain.community.dto.request.PostRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.application.UserService;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.validation.ValidationService;
import lombok.RequiredArgsConstructor;
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
}
