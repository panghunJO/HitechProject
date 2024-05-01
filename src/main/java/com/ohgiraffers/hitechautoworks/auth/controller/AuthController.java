// AuthController.java

package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/fail")
    public void fail(Model model, @RequestParam String message) {
        model.addAttribute("message", message);
    }

    //    @GetMapping("/idCheck")
//    public String overlappedID(@RequestParam String id, Model model) {
//        System.out.println("id = " + id);
//        String result = userService.overlappedID(id);
//        System.out.println("result = " + result);
//        model.addAttribute("result", result);
//        return "checkResult"; // 결과를 보여줄 View 이름을 반환
//    }
    @GetMapping("/idCheck")
    @ResponseBody // 컨트롤러에서 직접 응답을 반환하도록 설정
    public int overlappedID(@RequestParam String id) {
        String result = userService.overlappedID(id);
        System.out.println(result);
        int result1 = 0;
        if (result != null) {
            result1 = 1;
        } else {
            result1 = 0;
        }
        return result1; // int 값을 String으로 변환하여 반환
    }
}
