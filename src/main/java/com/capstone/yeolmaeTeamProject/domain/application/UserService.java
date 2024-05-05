package com.capstone.yeolmaeTeamProject.domain.application;

import com.capstone.yeolmaeTeamProject.domain.dao.UserRepository;
import com.capstone.yeolmaeTeamProject.domain.dto.request.UserRequestDto;
import com.capstone.yeolmaeTeamProject.domain.exception.AlreadyExistAccountException;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ValidationService validationService;

    private final UserRepository userRepository;

    public String createUser(UserRequestDto requestDto) {
        checkUserUniqueness(requestDto);
        User user = UserRequestDto.toEntity(requestDto);
        validationService.checkValid(user);
        return userRepository.save(user).getId();
    }

    private void checkUserUniqueness(UserRequestDto requestDto) {
        if (userRepository.existsById(requestDto.getId())) {
            throw new AlreadyExistAccountException("이미 존재하는 아이디입니다.");
        }
    }
}
