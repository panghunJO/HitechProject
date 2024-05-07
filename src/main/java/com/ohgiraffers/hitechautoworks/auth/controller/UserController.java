package com.ohgiraffers.hitechautoworks.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

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


}