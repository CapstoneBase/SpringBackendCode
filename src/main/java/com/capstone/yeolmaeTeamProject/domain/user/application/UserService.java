package com.capstone.yeolmaeTeamProject.domain.user.application;

import com.capstone.yeolmaeTeamProject.domain.user.dao.UserRepository;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserIdRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.dto.request.UserUpdateRequestDto;
import com.capstone.yeolmaeTeamProject.domain.user.dto.response.UserResponseDto;
import com.capstone.yeolmaeTeamProject.domain.user.exception.AlreadyExistAccountException;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.auth.util.AuthUtil;
import com.capstone.yeolmaeTeamProject.global.exception.NotFoundException;
import com.capstone.yeolmaeTeamProject.global.validation.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Boolean checkId(UserIdRequestDto requestDto) {
        return userRepository.existsById(requestDto.getId());
    }

    @Transactional(readOnly = true)
    public UserResponseDto getMyProfile() {
        User user = getCurruentUser();
        return UserResponseDto.toDto(user);
    }

    public UserResponseDto getUserProfile(String userId) {
        User user = getUserByIdOrThrow(userId);
        return UserResponseDto.toDto(user);
    }

    @Transactional
    public String updateUser(UserUpdateRequestDto requestDto) {
        User user = getCurruentUser();
        user.update(requestDto, passwordEncoder);
        validationService.checkValid(user);
        return userRepository.save(user).getId();
    }

    @Transactional
    public String deleteUser() {
        User user = getCurruentUser();
        userRepository.delete(user);
        return user.getId();
    }

    private void checkUserUniqueness(UserRequestDto requestDto) {
        if (userRepository.existsById(requestDto.getId())) {
            throw new AlreadyExistAccountException("이미 존재하는 아이디입니다.");
        }
    }

    public User getCurruentUser() {
        String userId = AuthUtil.getAuthenticationInfoUserId();
        return getUserByIdOrThrow(userId);
    }

    private User getUserByIdOrThrow(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));
    }
}
