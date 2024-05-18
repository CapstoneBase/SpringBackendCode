package com.capstone.yeolmaeTeamProject.domain.user.dto.request;

import com.capstone.yeolmaeTeamProject.domain.user.domain.Role;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @NotNull(message = "아이디를 입력해주세요.")
    @Schema(description = "아이디", example = "yeolmae")
    private String id;

    @NotNull(message = "비밀번호를 입력해주세요.")
    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @NotNull(message = "이름을 입력해주세요.")
    @Schema(description = "이름", example = "홍길동")
    private String name;

////    현재 필수는 아니게 설정
//    @Schema(description = "이메일", example = "abc@abc.com")
//    private String email;
//
////    현재 필수는 아니게 설정
//    @Schema(description = "학교", example = "한국대학교")
//    private String school;
//
////    현재 필수는 아니게 설정
//    @Schema(description = "전공", example = "컴퓨터공학")
//    private String major;

    public static User toEntity(UserRequestDto requestDto) {
        return User.builder()
                .id(requestDto.getId())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
//                .email(requestDto.getEmail())
//                .school(requestDto.getSchool())
//                .major(requestDto.getMajor())
                .role(Role.USER)
                .build();
    }
}
