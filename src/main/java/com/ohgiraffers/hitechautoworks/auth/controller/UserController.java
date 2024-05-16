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
        model.addAttribute("userDTO", userDTO);
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
        model.addAttribute("userDTO", userDTO);
    }

    @PostMapping("/user/mypage/update")
    public String updateUser(@RequestParam Map<String,String> myprofile) {

        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode(); // 기준으로

        myprofile.put("userCode", String.valueOf(userCode));
        userService.updateUser(myprofile);

        return "redirect:/user/mypage";
    }

    }


