package com.ohgiraffers.hitechautoworks.auth.controller;


import com.ohgiraffers.hitechautoworks.auth.dto.*;

import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private AuthUserInfo authUserInfo;

    @Autowired
    private UserService userService;

    @GetMapping("/user/dashboard")
    public void dashboard(Model model) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName", userName);
    }

    @GetMapping("/customer/dashboard")
    public void dashboard2(Model model) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName", userName);
    }

    @GetMapping("/employee/dashboard")
    public void employee(Model model) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName", userName);
    }

    @GetMapping("/customer/account/AccountModify")
    public void AccountModify(Model model,HttpSession session) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        UserRegistDTO registDTO = userService.getAll(userDTO.getUserCode());
        System.out.println("registDTO = " + registDTO);
        model.addAttribute("userRegistDTO", registDTO);
        String message = (String) session.getAttribute("errorMessage");
        model.addAttribute("errorMessage", message);
        session.removeAttribute("errorMessage");
    }

    @PostMapping("/customer/account/AccountModify")
    public String account(@RequestParam String userId, @RequestParam String userPw, @RequestParam String userPwCheck, @RequestParam String userName,
                          @RequestParam String userEmail, @RequestParam String userAddress, @RequestParam String userPhone, Model model,
                          HttpSession session) {
        userService.updateUser(userId, userPw, userEmail, userPwCheck, userAddress, userPhone, userName);
        if (!userPw.equals(userPwCheck)) {
            // 비밀번호 확인이 일치하지 않을 경우 에러 메시지 전달하고 리다이렉트
            session.setAttribute("errorMessage","비밀번호가 일치하지 않습니다.");
            return "redirect:/customer/account/AccountModify";

        } else {
            userService.updateUser(userId, userPw, userEmail, userPwCheck, userAddress, userPhone, userName);
            return "customer/res/res";
        }

    }

    @PostMapping("/customer/account/deleteUser")
    public String deleteUser() {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode= userDTO.getUserCode();
        userService.deletePeople(userCode);

        return "customer/res/res";

    }

}


