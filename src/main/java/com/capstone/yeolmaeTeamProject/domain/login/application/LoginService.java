package com.capstone.yeolmaeTeamProject.domain.login.application;

import com.capstone.yeolmaeTeamProject.domain.login.dto.request.LoginRequestDto;
import com.capstone.yeolmaeTeamProject.domain.login.dto.response.TokenInfo;
import com.capstone.yeolmaeTeamProject.domain.user.dao.UserRepository;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.auth.jwt.JwtTokenProvider;
import com.capstone.yeolmaeTeamProject.global.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public TokenInfo login(LoginRequestDto requestDto) {
        // UsernamePasswordAuthenticationToken: Authentication을 구현한 클래스, 사용자의 ID와 Password를 저장
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestDto.getId(), requestDto.getPassword());

        // AuthenticationManager: 인증을 수행하는 인터페이스, token을 이용하여 인증을 수행
        // global쪽에서의 CustomUserDetailsService -> loadUserByUsername 메소드를 호출하여 사용자 정보를 가져옴
        Authentication authentication = authenticationManager.authenticate(token);

        Optional<User> userOpt = userRepository.findById(requestDto.getId());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return jwtTokenProvider.generateToken(user.getId(), user.getRole());
        }

        return null;

    }

    public TokenInfo reissue(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        validateUserExistence(authentication);

        if (jwtTokenProvider.validateToken(token)) {
            return jwtTokenProvider.reissue(token);
        }
        return null;
    }

    private void validateUserExistence(Authentication authentication) {
        String id = authentication.getName();
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자의 토큰입니다."));
    }

}
