package com.ohgiraffers.hitechautoworks.account.controller;

import com.ohgiraffers.hitechautoworks.account.service.AccountService;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("userDTO")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    private AuthUserInfo authUserInfo;


    @ModelAttribute
    public void addUserToModel(Model model){
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO",userDTO1);
    }

    @GetMapping("/user/employeePage")
    public String account(Model model) {
        List<UserDTO> userList = accountService.findAllUser();
        model.addAttribute("userList", userList);
        return "user/employeePage";
    }

    @GetMapping("/user/adminEmployee")
    public String accountEmp(Model model) {
        List<UserDTO> userList = accountService.findAllUser();
        model.addAttribute("userList", userList);
        return "user/adminEmployee";
    }

    @PostMapping("/user/empSearch")
    public String account2(@RequestParam String userName, @RequestParam String userCode, Model model) {

        if (userName == "" && userCode == "") {
            List<UserDTO> userList = accountService.findAllUser();
            model.addAttribute("userList", userList);

        } else if (userName == "") {
            List<UserDTO> userList = accountService.findUserCode(userCode);
            model.addAttribute("userList", userList);

        } else {
            List<UserDTO> userList = accountService.findUserName(userName);
            model.addAttribute("userList", userList);
        }
        return "user/employeePage";

    }

}
