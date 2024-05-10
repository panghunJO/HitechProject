package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.ResDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.EtcCarDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.ImportantDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.NormalDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.SomoDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ResController {

    @Autowired
    private UserService userService;

    private AuthUserInfo authUserInfo;

    @GetMapping("/customer/res/res")
    public String res(Model moder) {
        List<ResDTO> resList = userService.findAllres();
        System.out.println("resList = " + resList);
        moder.addAttribute("resList", resList);
        return "customer/res/res";
    }

    @GetMapping("/customer/res/resDetail")
    public void resdetail(@RequestParam int resCode, Model model) {
        System.out.println("resCode = " + resCode);
        ResDTO res = userService.findUserRes(resCode);
        System.out.println("res = " + res);
        model.addAttribute("res", res);
    }

    @GetMapping("/customer/res/res_01")
    public void res01() {
    }

    @GetMapping("/customer/res/res_02")
    public void res02() {
    }

    @GetMapping("/customer/res/res_03")
    public void res03() {
    }

    @GetMapping("/customer/res/res_04")
    public void res04() {
    }

    @GetMapping("/customer/res/res_05")
    public void res05() {
    }

    @GetMapping("/customer/res/res_06")
    public void res06(@ModelAttribute SomoDTO somoDTO, Model model,
                      @ModelAttribute ImportantDTO importantDTO,
                      @ModelAttribute NormalDTO normalDTO,
                      @ModelAttribute EtcCarDTO etcCarDTO,
                      HttpSession httpSession) {
        List<String> partAllList = (List<String>) httpSession.getAttribute("partAllList");

        List<String> partList = somoDTO.getNonNullValues();
        List<String> partList2 = importantDTO.getNonNullValues();
        List<String> partList3 = normalDTO.getNonNullValues();
        List<String> partList4 = etcCarDTO.getNonNullValues();

        if (partAllList == null) {
            partAllList = new ArrayList<>();
        }

        partAllList.addAll(partList);
        partAllList.addAll(partList2);
        partAllList.addAll(partList3);
        partAllList.addAll(partList4);

        httpSession.setAttribute("partAllList", partAllList);

        model.addAttribute("partlist", partAllList);
    }

    @GetMapping("/customer/res/res_07")
    public void res07() {
    }

    @GetMapping("/customer/res/res_08")
    public void res08() {
    }
}
