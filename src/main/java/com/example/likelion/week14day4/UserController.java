package com.example.likelion.week14day4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {


    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }


    // 로그인 여부를 판단하기 위한 GetMapping
    @GetMapping("/my-profile")
    public String myProfile() {
        return "my-profile";
    }

    // 1. 사용자가 register 페이지로 온다.
    // 2. 사용자가 resgister 페이지에 ID, 비밀번호, 비밀번호 확인을 입력
    // 3. register 페이지에서 /users/register 로 Post 입력
    // 4. UserDetailManager 에 새로운 사용자 정보 추가

    @GetMapping("register")
    public String registerForm() {
        return "register-form";
    }

}
