package com.ohgiraffers.hitechautoworks.account.controller;

import com.ohgiraffers.hitechautoworks.account.service.AccountService;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
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

    private AuthUserInfo authUserInfo;



    @GetMapping("/employee/account/account")
    public String account(Model model) {
        List<UserDTO> userList = accountService.findAllUser();
        System.out.println("userList = " + userList);
        model.addAttribute("userList", userList);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName",userName);
        return "employee/account/account";
    }

    @PostMapping("/employee/account/account")
    public void account2(@RequestParam String userName, @RequestParam String userCode, Model model) {

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


}
