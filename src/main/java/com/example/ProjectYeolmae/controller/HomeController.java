package com.example.ProjectYeolmae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //맨 처음 화면 요청
    @GetMapping("/")
    public String index() {
        return "index"; //index.html로 mapping
    }

}
