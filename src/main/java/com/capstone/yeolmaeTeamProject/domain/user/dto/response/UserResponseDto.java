package com.capstone.yeolmaeTeamProject.domain.user.dto.response;

import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

    private String name;

    private String email;

    private String school;

    private String major;

    public static UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                //회원정보 추가 시 더 많은 정보 입력할 수 있게 하게끔
//                .email(user.getEmail())
//                .school(user.getSchool())
//                .major(user.getMajor())
                .build();
    }
}
