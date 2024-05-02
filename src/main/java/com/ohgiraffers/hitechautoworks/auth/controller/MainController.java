package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.MailDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
import com.ohgiraffers.hitechautoworks.auth.service.AuthDetails;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.MailService;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class MainController {

    private final UserService userService;
    private final MailService mailService;
    private AuthUserInfo authUserInfo;

    @Autowired
    public MainController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @GetMapping(value = {"/main", "/", "/login", "member/login"})
    public String main() {
        return "/member/login";
    }

    @GetMapping("/certified/loading")
    public String loading() {
        return "certified/loading";
    }

    @PostMapping("/member/regist")
    public String login(@ModelAttribute UserRegistDTO registDTO, Model model, @RequestParam String email, HttpServletResponse response) throws IOException {

        MailDTO mailDTO = new MailDTO();
        mailDTO.setAddress(email);
        mailDTO.setTitle("회원 인증 코드입니다!!");
        int randomNumber = (int) (Math.random() * 1000000); // 100만 이하의 랜덤 숫자 생성
        while (randomNumber < 100000) { // 6자리 숫자가 아니면 다시 생성
            randomNumber = (int) (Math.random() * 1000000);
        }
        mailDTO.setMessage("인증코드는 " + randomNumber + "입니다!");
        System.out.println("randomNumber = " + randomNumber);
        System.out.println("확인");
        mailService.check(mailDTO);
        registDTO.setUserCheck(randomNumber);

        int result = userService.regist(registDTO);
        if (result == 1) {
            model.addAttribute("successMessage", "회원가입이 성공적으로 완료되었습니다."); // 성공 메시지를 모델에 추가합니다
        }
        System.out.println("1231231321 = " + 1231231321);
        return "redirect:/certified/loading";
    }

    @GetMapping("/member/regist")
    public String regist() {

        return "/member/regist";
    }

    @GetMapping("/user/dashboard")
    public void dashboard(Model model) {
    }

    @GetMapping("/customer/dashboard")
    public void dashboard2(Model model) {}

    @GetMapping("/employee/dashboard")
    public void employee() {}

    @GetMapping("/certified/checkinfo")
    public void certifiedCheckInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("authentication = " + authentication);
//        System.out.println("정보들 : " + authentication.getPrincipal());
//        AuthDetails authDetails = (AuthDetails) authentication.getPrincipal();
//        System.out.println("authDetails.getLoginUserDTO() = " + authDetails.getLoginUserDTO());
//        System.out.println("authDetails = " + authDetails.getUsername());
    }

    @PostMapping("/certified/checkinfo")
    public String check(@RequestParam int checknumber) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        System.out.println("userDTO = " + userDTO);
        int userCode = userDTO.getUserCode();
        int result = userService.findcheck(checknumber,userCode);
        System.out.println("result = " + result);
        return "/member/login";
    }
}
