package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CertifiedController {

    private final UserService userService;
    private AuthUserInfo authUserInfo;

    @Autowired
    public CertifiedController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/certified/loading")
    public String loading() {
        return "certified/loading";
    }



    @GetMapping("/certified/checkinfo")
    public String check(@RequestParam int checknumber, @RequestParam String userId) {

        int result = userService.findcheck(checknumber,userId);
        return "member/login";
    }


    @GetMapping("certified/certifiedError")
    public String certified() {

        return "error/certified";
    }

}
