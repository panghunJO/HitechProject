package com.ohgiraffers.hitechautoworks.auth.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.MailDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
import com.ohgiraffers.hitechautoworks.auth.service.MailService;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
public class MainController {

    @Value("${kakao.client_id}")
    private String client_id;

    @Value("${kakao.redirect_uri}")
    private String redirect_uri;

    private final UserService userService;
    private final MailService mailService;
    @Autowired
    public MainController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
    }

    @GetMapping(value = {"/main", "/", "/login", "member/login"})
    public String main(Model model) {
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+client_id+"&redirect_uri="+redirect_uri;
        model.addAttribute("location", location);
        return "member/login";
    }

//    @PostMapping("/member/regist")
//    public String login(@ModelAttribute UserRegistDTO registDTO, Model model, @RequestParam String email, HttpServletResponse response) throws IOException {
//
//        MailDTO mailDTO = new MailDTO();
//        mailDTO.setAddress(email);
//        mailDTO.setTitle("회원 인증 코드입니다!!");
//        int randomNumber = (int) (Math.random() * 1000000);
//        while (randomNumber < 100000) {
//            randomNumber = (int) (Math.random() * 1000000);
//        }
//        mailDTO.setMessage("인증코드는 " + randomNumber + "입니다!");
//        mailService.check(mailDTO);
//        registDTO.setUserCheck(randomNumber);
//
//        int result = userService.regist(registDTO);
//        if (result == 1) {
//            model.addAttribute("successMessage", "회원가입이 성공적으로 완료되었습니다."); // 성공 메시지를 모델에 추가합니다
//        }
//        return "redirect:/certified/loading";
//    }

    @PostMapping("/member/regist")
    public String login(@ModelAttribute UserRegistDTO registDTO, Model model, @RequestParam String email, HttpServletResponse response, HttpServletRequest request) throws IOException {

        MailDTO mailDTO = new MailDTO();
        mailDTO.setAddress(email);
        mailDTO.setTitle("Hitech Autoworks 인증메일입니다.");
        int randomNumber = (int) (Math.random() * 1000000);
        while (randomNumber < 100000) {
            randomNumber = (int) (Math.random() * 1000000);
        }
        String content =
                "나의 APP을 방문해주셔서 감사합니다." + 	//html 형식으로 작성 !
                        "<br><br>" +
                        "<a href=\"http://localhost:8080/certified/checkinfo?checknumber=" + randomNumber + "&userId=" + registDTO.getUserid() + "\">인증하기</a>" +
                        "<br>";
        mailDTO.setMessage(content);

        // 메일 전송을 CompletableFuture로 감싸기
        CompletableFuture<Void> mailSending = CompletableFuture.runAsync(() -> {
            try {
                mailService.check(mailDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // 메일 전송이 완료된 후에 후속 작업 실행
        int finalRandomNumber = randomNumber;
        mailSending.thenRun(() -> {
            registDTO.setUserCheck(finalRandomNumber);

            int result = userService.regist(registDTO);
            if (result == 1) {
                model.addAttribute("successMessage", "회원가입이 성공적으로 완료되었습니다."); // 성공 메시지를 모델에 추가합니다
            }
        });

        return "redirect:/certified/loading";
    }


    @GetMapping("/member/regist")
    public String regist() {

        return "/member/regist";
    }


}
