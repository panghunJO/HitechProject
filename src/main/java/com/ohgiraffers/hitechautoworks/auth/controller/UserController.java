package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.PartDTO;
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

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    public void part2(@RequestParam String partName, @RequestParam String partCode,
                      Model model){
        System.out.println("partName = " + partName);
        System.out.println("partCode = " + partCode);
        if (partName == "" && partCode == ""){
            List<PartDTO> partList = userService.selectAllPart();
            model.addAttribute("partList", partList);
            System.out.println("partList = " + partList);
        } else {
            List<PartDTO> partList = userService.selectPartByCode(Integer.parseInt(partCode));
        System.out.println("partList = " + partList);
        model.addAttribute("partList", partList);
        }
    }

    @GetMapping("/employee/part/partdetail")
    public void partdetail(@RequestParam int partCode,
                           Model model){
        PartDTO partDTO = userService.selectpart(partCode);
        System.out.println("partDTO = " + partDTO);
        model.addAttribute("partDTO", partDTO);

    }



}