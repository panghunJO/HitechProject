package com.ohgiraffers.hitechautoworks.repair.controller;

import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import com.ohgiraffers.hitechautoworks.repair.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RepairController {

    @Autowired
    private RepairService repairService;

    private AuthUserInfo authUserInfo;

    @GetMapping("/employee/repair/repair")
    public void repair(Model model){
        List<RepairDTO> repairList = repairService.findAllRepair();
        System.out.println("repairList = " + repairList);
        model.addAttribute("repairList", repairList);


    }

//    @PostMapping("/employee/repair/repair")
//    public String repair(@RequestParam String userName, @RequestParam int userCode){
//        System.out.println("userName = " + userName);
//        System.out.println("userCode = " + userCode);
//
//        List<RepairDTO> repairList = repairService.findRepairInfo();
//
//
//        return "/employee/repair/repair";
//    }


}
