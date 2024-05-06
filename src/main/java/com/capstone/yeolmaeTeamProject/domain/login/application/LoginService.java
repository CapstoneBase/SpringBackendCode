package com.capstone.yeolmaeTeamProject.domain.login.application;

import com.capstone.yeolmaeTeamProject.domain.login.dto.request.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    public String login(LoginRequestDto requestDto) {
        // UsernamePasswordAuthenticationToken: Authentication을 구현한 클래스, 사용자의 ID와 Password를 저장
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(requestDto.getId(), requestDto.getPassword());

        // AuthenticationManager: 인증을 수행하는 인터페이스, token을 이용하여 인증을 수행
        // global쪽에서의 CustomUserDetailsService -> loadUserByUsername 메소드를 호출하여 사용자 정보를 가져옴
        Authentication authentication = authenticationManager.authenticate(token);

        // SecurityContextHolder: SecurityContext를 제공하는 클래스, SecurityContext에 인증 정보를 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return requestDto.getId();
    }

}
