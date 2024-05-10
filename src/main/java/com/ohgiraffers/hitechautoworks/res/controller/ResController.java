package com.ohgiraffers.hitechautoworks.res.controller;

import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import com.ohgiraffers.hitechautoworks.res.service.ResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ResController {


    @Autowired
    private ResService resService;

    private AuthUserInfo authUserInfo;

    @GetMapping("/customer/res/res")
    public String res(Model moder){
        List<ResDTO> resList = resService.findAllres();
        System.out.println("resList = " + resList);
        moder.addAttribute("resList", resList);
        return "customer/res/res";
    }
    @GetMapping("/customer/res/resDetail")
    public void resdetail(@RequestParam int resCode, Model model){
        System.out.println("resCode = "+resCode);
        ResDTO res = resService.findUserRes(resCode);
        System.out.println("res = " + res);
        model.addAttribute("res", res);
    }
    @PostMapping("/customer/res/res")
    public void res1(@RequestParam int resCode, Model model){
        System.out.println("resCode = " + resCode);
        List<ResDTO> resList = resService.findCodeRes(resCode);
        System.out.println("resList = " + resList);
        model.addAttribute("resList", resList);

    }
}
