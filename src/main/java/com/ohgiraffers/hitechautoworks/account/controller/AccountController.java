package com.ohgiraffers.hitechautoworks.account.controller;

import com.ohgiraffers.hitechautoworks.account.service.AccountService;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    private AuthUserInfo authUserInfo;



    @GetMapping("/user/employee")
    public String account(Model model) {
        List<UserDTO> userList = accountService.findAllUser();
        System.out.println("userList = " + userList);
        model.addAttribute("userList", userList);
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);
        return "user/employeePage";
    }

    @PostMapping("/user/empSearch")
    public String account2(@RequestParam String userName, @RequestParam String userCode, Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode1 = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode1);
        model.addAttribute("userDTO", userDTO1);
        System.out.println("userName = " + userName);
        System.out.println("userCode = " + userCode);

        if(userName == "" && userCode == "") {
            List<UserDTO> userList = accountService.findAllUser();
            model.addAttribute("userList", userList);
            System.out.println("userList = " + userList);

        } else if (userName == "") {
            List<UserDTO> userList = accountService.findUserCode(userCode);
            System.out.println("userList = " + userList);
            model.addAttribute("userList", userList);

        } else {
            List<UserDTO> userList = accountService.findUserName(userName);
            System.out.println("userList = " + userList);
            model.addAttribute("userList", userList);
        }
            return "user/employeePage";

    }

    @GetMapping("/admin/account/account")
    public String accountAd(Model model) {
        List<UserDTO> userList = accountService.findAllUser();
        System.out.println("userList = " + userList);
        model.addAttribute("userList", userList);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName",userName);
        return "admin/account/account";
    }

    // 수정
    @GetMapping("/admin/account/AccountModify")
    public void AccountModify(@RequestParam int userCode, Model model, HttpSession session) {

        System.out.println("userCode = " + userCode);
        UserDTO userDTO = accountService.selectAccount(userCode);
        System.out.println("userDTO = " + userDTO);
        model.addAttribute("userDTO", userDTO);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO1 = authUserInfo.getUserDTO();
        String userName = userDTO1.getUserName();
        model.addAttribute("userName",userName);
    }

    @PostMapping("/admin/account/AccountModify")
    public String account(@RequestParam String userId, @RequestParam int userCode,@RequestParam String userDepartment, @RequestParam String userPw, @RequestParam String userPwCheck, @RequestParam String userName,
                          @RequestParam String userEmail, @RequestParam String userAddress, @RequestParam String userPhone, Model model,
                          HttpSession session) {
        System.out.println("userId = " + userId);
        System.out.println("userPw = " + userPw);
        System.out.println("userPwCheck = " + userPwCheck);
        System.out.println("userDepartment = " + userDepartment);
        accountService.updateUser(userId, userCode, userDepartment, userPw, userEmail, userPwCheck, userAddress, userPhone, userName);
        if (!userPw.equals(userPwCheck)) {
            // 비밀번호 확인이 일치하지 않을 경우 에러 메시지 전달하고 리다이렉트
            session.setAttribute("errorMessage","비밀번호가 일치하지 않습니다.");
            return "redirect:/admin/account/account";

        } else {
            accountService.updateUser(userId, userCode, userDepartment, userPw, userEmail, userPwCheck, userAddress, userPhone, userName);
            return "redirect:/admin/account/account";
        }
    }

    @PostMapping("/admin/account/AccountDelete")
    public String accountDeleteUser(@RequestParam int userCode) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        accountService.accountDeleteUser(userCode);

        return "redirect:/admin/account/account";

    }





}
