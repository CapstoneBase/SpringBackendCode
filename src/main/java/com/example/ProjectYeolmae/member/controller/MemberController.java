package com.example.ProjectYeolmae.member.controller;

import com.example.ProjectYeolmae.member.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("/users/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/users/signup")
    public String signup(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.signup");
        System.out.println("memberDTO = " + memberDTO);
        return "index";
    }


}
