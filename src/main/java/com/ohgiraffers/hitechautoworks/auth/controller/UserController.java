package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/dashboard")
    public void dashboard(Model model) {
    }

    @GetMapping("/customer/dashboard")
    public void dashboard2(Model model) {}

    @GetMapping("/employee/dashboard")
    public void employee() {}

    @GetMapping("/employee/part/part")
    public void part(){}

    @PostMapping("/employee/part/part")
    public void part2(@RequestParam String partName, @RequestParam int partCode){
        System.out.println("partName = " + partName);
        System.out.println("partCode = " + partCode);
    }

    @GetMapping("/employee/part/partAdd")
    public void partAdd(){}

//    @GetMapping("/employee/repair/repair")
//    public void repair(){}
//
//    @PostMapping("/employee/repair/repair")
//    public String repair(@RequestParam String userName, @RequestParam int userCode){
//        System.out.println("userName = " + userName);
//        System.out.println("userCode = " + userCode);
//
//        List<RepairDTO> repairList = UserService.findRepairInfo();
//
//
//        return "/employee/repair/repair";
//    }
    @GetMapping("/employee/account/account")
    public String account(Model model){
        List<UserDTO> userList = userService.findAllUser();
        System.out.println("userList = " + userList);
        model.addAttribute("userList", userList);
        return "employee/account/account";

    }




    }


