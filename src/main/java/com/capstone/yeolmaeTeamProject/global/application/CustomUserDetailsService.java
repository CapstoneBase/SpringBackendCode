package com.capstone.yeolmaeTeamProject.global.application;

import com.capstone.yeolmaeTeamProject.domain.user.dao.UserRepository;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 이름을 기반으로 사용자 정보를 가져옴
        // 사용자가 입력한 PW를 암호화하여 DB에 저장된 PW와 비교하도록 설계
        return userRepository.findById(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(User user) {
        return User.createUserDetails(user);
    }

}
