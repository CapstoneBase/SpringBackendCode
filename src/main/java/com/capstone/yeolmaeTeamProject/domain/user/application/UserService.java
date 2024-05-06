package com.capstone.yeolmaeTeamProject.domain.user.application;

import com.capstone.yeolmaeTeamProject.domain.user.dao.UserRepository;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserIdRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.exception.AlreadyExistAccountException;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ValidationService validationService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public String createUser(UserRequestDto requestDto) {
        checkUserUniqueness(requestDto);
        User user = UserRequestDto.toEntity(requestDto);
        user.encryptPassword(passwordEncoder);
        validationService.checkValid(user);
        return userRepository.save(user).getId();
    }

    public Boolean checkId(UserIdRequestDto requestDto) {
        return userRepository.existsById(requestDto.getId());
    }


    private void checkUserUniqueness(UserRequestDto requestDto) {
        if (userRepository.existsById(requestDto.getId())) {
            throw new AlreadyExistAccountException("이미 존재하는 아이디입니다.");
        }
    }
}
