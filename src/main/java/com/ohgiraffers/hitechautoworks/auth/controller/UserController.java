package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.PartDTO;
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
    public void dashboard2(Model model) {
    }

    @GetMapping("/employee/dashboard")
    public void employee() {
    }

    @GetMapping("/employee/part/part")
    public void part(Model model) {
        List<PartDTO> partList = userService.selectAllPart();
        model.addAttribute("partList", partList);
    }

    @PostMapping("/employee/part/part")
    public void part2(@RequestParam String partName, @RequestParam String partCode,
                      Model model) {
        System.out.println("partName = " + partName);
        System.out.println("partCode = " + partCode);
        if (partName == "" && partCode == "") {
            List<PartDTO> partList = userService.selectAllPart();
            model.addAttribute("partList", partList);
            System.out.println("partList = " + partList);
        } else if (partName == "") {
            List<PartDTO> partList = userService.selectPartByCode(Integer.parseInt(partCode));
            System.out.println("partList = " + partList);
            model.addAttribute("partList", partList);
        } else {
            List<PartDTO> partList = userService.partSearchBtPartName(partName);
            System.out.println("partList = " + partList);
            model.addAttribute("partList", partList);
        }
    }

    @GetMapping("/employee/part/partdetail")
    public void partdetail(@RequestParam int partCode,
                           Model model) {
        PartDTO partDTO = userService.selectpart(partCode);
        System.out.println("partDTO = " + partDTO);
        model.addAttribute("partDTO", partDTO);

    }

    @GetMapping("/employee/part/partAdd")
    public void partAdd() {
    }

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
    public String account(Model model) {
        List<UserDTO> userList = userService.findAllUser();
        System.out.println("userList = " + userList);
        model.addAttribute("userList", userList);
        return "employee/account/account";

    }

    @GetMapping("/admin/account/account")
    public String accountAd(Model model) {
        List<UserDTO> userList = userService.findAllUser();
        System.out.println("userList = " + userList);
        model.addAttribute("userList", userList);
        return "admin/account/account";
    }

//    @PostMapping("/employee/account/account")
//    public void account2(@RequestParam String userId, @RequestParam String user_code, Model model) {
//        System.out.println("userId = " + userId);
//        System.out.println("user_code = " + user_code);
//
//        if(userId == "" && user_code == "") {
//            List<UserDTO> userList = userService.findAllUser();
//            model.addAttribute("userList", userList);
//            System.out.println("userList = " + userList);
//
//        } else if (userId == "") {
//            List<UserDTO> userList = userService.findUserId(String.valueOf(Integer.parseInt(user_code));
//            System.out.println("userList = " + userList);
//            model.addAttribute("userList", userList);
//
//        } else {
//            List<UserDTO> userList = userService.findUserCode(userName);
//            System.out.println("userList = " + userList);
//            model.addAttribute("userList", userList);
//        }
//
//    }
//
//}
    @PostMapping("/employee/account/account")
    public void account2(@RequestParam String userId, @RequestParam String user_code, Model model) {
        System.out.println("userId = " + userId);
        System.out.println("user_code = " + user_code);

        if (userId.equals("") && user_code.equals("")) {
            List<UserDTO> userList = userService.findAllUser();
            model.addAttribute("userList", userList);
            System.out.println("userList = " + userList);

        } else if (userId.equals("")) {
            if (!user_code.equals("")) {
                List<UserDTO> userList = userService.findUserId(user_code);
                System.out.println("userList = " + userList);
                model.addAttribute("userList", userList);
            } else {
                // handle the case where user_code is empty
            }
        } else {
            // handle the case where userId is not empty
        }
    }
}


