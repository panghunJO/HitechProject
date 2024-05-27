package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.SMSUtil;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private SMSUtil smsUtil;

    @GetMapping("/fail")
    public void fail(Model model, @RequestParam String message) {
        model.addAttribute("message", message);
    }

    @GetMapping("/idCheck")
    @ResponseBody
    public int overlappedID(@RequestParam String id) {
        String result = userService.overlappedID(id);
        int result1 = 0;
        if (result != null) {
            result1 = 1;
        } else {
            result1 = 0;
        }
        return result1; // int 값을 String으로 변환하여 반환
    }

    @PostMapping("/searchForId")
    @ResponseBody
    public Map<String,Object> searchForId(@RequestBody Map<String,Object> info) {

        Map<String,Object> stat = userService.searchForId(info.get("email"));

        if (stat == null) {
            stat = new HashMap<>();
        }
        System.out.println("stat = " + stat);

        return stat;
    }

    @PostMapping("/searchForPW")
    @ResponseBody
    public Map<String,Object> searchForPW(@RequestBody Map<String,Object> info) {

        String phone = info.get("phone").toString();
        String formattedPhone = phone.replace("-", "");
        Map<String,Object> stat = userService.searchForPW(info);

        int newPassword = (int) stat.get("newPass");

        smsUtil.sendOne(formattedPhone,newPassword);

        return stat;
    }
}
