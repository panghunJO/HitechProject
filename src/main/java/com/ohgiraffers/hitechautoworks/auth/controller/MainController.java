package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/main", "/", "/login","member/login"})
    public String main() {
        return "/member/login";
    }

    //    @PostMapping("/login")
//    public String login(@ModelAttribute UserRegistDTO registDTO, Model model) {
//        int result = userService.regist(registDTO);
//        System.out.println("result = " + result);
//        return "/login";
//    }
    @PostMapping("/member/regist")
    public String login(@ModelAttribute UserRegistDTO registDTO, Model model) {
        int result = userService.regist(registDTO);
        if(result == 1) {
            model.addAttribute("successMessage", "회원가입이 성공적으로 완료되었습니다."); // 성공 메시지를 모델에 추가합니다
        }
        return "/member/login";
    }

    @GetMapping("/member/regist")
    public String regist() {

        return "/member/regist";
    }

    @GetMapping("/user/dashboard")
    public void dashboard(Model model) {}
}
