package com.ohgiraffers.hitechautoworks.auth.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.hitechautoworks.auth.dto.*;

import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/user/mainpage")
    public void mainpage(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        System.out.println("userDTO1 = " + userDTO1);
        model.addAttribute("userDTO", userDTO1);
    }

    @GetMapping("/user/mypage")
    public void mypage(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        System.out.println("userDTO1 = " + userDTO1);
        model.addAttribute("userDTO", userDTO1);
        System.out.println("userDTO = " + userDTO);
    }

    @PostMapping("/user/mypage/update")
    public String updateUser(@RequestParam Map<String,String> myprofile) {

        for (Map.Entry<String, String> entry : myprofile.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        return "redirect:/user/mypage";
    }

    @PostMapping(value = "/user/mypage/changepass", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String changepass(@RequestBody PasswordRequestDTO request) {
        System.out.println("request = " + request);
        String currentPassword = request.getCurrentPassword();
        String newPassword = request.getNewPassword();
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        String pw = userDTO1.getPassword();
        String result = String.valueOf(userService.changepass(currentPassword,newPassword, pw, userCode));


        return result;
    }

    @GetMapping("/user/testpage")
    public String testPage(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        model.addAttribute("userDTO", userDTO);
        return "/user/testPage";
    }

    @GetMapping("/user/common")
    public void common(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        System.out.println("userDTO1 = " + userDTO1);
        model.addAttribute("userDTO", userDTO1);
    }

    @GetMapping("/user/adminaccountedit")
    public void adminAccountEdit(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        System.out.println("userDTO1 = " + userDTO1);
        model.addAttribute("userDTO", userDTO1);
    }


}


