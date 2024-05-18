package com.capstone.yeolmaeTeamProject.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {

    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @Schema(description = "이름", example = "홍길동")
    private String name;

//    회원가입 부분 입력 파라키터 추가 시 사용할 schema
//    @Schema(description = "이메일", example = "abc@abc.com")
//    private String email;
//
//    @Schema(description = "학교", example = "한국대학교")
//    private String school;
//
//    @Schema(description = "전공", example = "컴퓨터공학")
//    private String major;

}
