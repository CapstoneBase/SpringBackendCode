package com.example.ProjectYeolmae.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {    //회원정보에 필요한 내용 정보들을 필드 값으로 선언해 놓고 사용
    private Long id;                //고유식별 id
    private String memberName;      //아이디
    private String memberPassword;  //비밀번호
    private String memberEmail;     //이메일
    private String memberUsername;  //이름
    private String memberSchool;    //학교
    private String memberMajor;     //학과
}
