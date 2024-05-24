package com.ohgiraffers.hitechautoworks.auth.controller;


import com.ohgiraffers.hitechautoworks.auth.dto.*;

import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.part.service.PartService;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.res.dto.DeleteCommentDTO;
import com.ohgiraffers.hitechautoworks.res.dto.EditCommentDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResCommentDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import com.ohgiraffers.hitechautoworks.res.service.ResService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;

@Controller
@SessionAttributes("userDTO")
public class UserController {

    private AuthUserInfo authUserInfo;

    @Autowired
    private UserService userService;

    @Autowired
    private ResService resService;

    @ModelAttribute
    public void addUserToModel(Model model){
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO",userDTO1);
    }

    @GetMapping("/user/rescustomer")
    public void resccustomer(Model model) {

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        List<ResDTO> resList = resService.findCustomerRes(userCode);
        model.addAttribute("resList",resList);
    }
    @GetMapping("/user/common")
    public void common(Model model) {
        List<ResDTO> resList = resService.findAllres();
        System.out.println("resList = " + resList);
        model.addAttribute("resList", resList);
    }

    @GetMapping("/user/dashboard")
    public void dashboard(Model model) {
        String userName = ((UserDTO) model.getAttribute("userDTO")).getUserName();
        model.addAttribute("userName", userName);
    }

    @GetMapping("/customer/dashboard")
    public void dashboard2(Model model) {
        String userName = ((UserDTO) model.getAttribute("userDTO")).getUserName();
        model.addAttribute("userName", userName);
    }

    @GetMapping("/employee/dashboard")
    public void employee(Model model) {
        String userName = ((UserDTO) model.getAttribute("userDTO")).getUserName();
        model.addAttribute("userName", userName);
    }


    @GetMapping("/customer/account/AccountModify")
    public void AccountModify(Model model, HttpSession session) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        UserRegistDTO registDTO = userService.getAll(userDTO.getUserCode());
        System.out.println("registDTO = " + registDTO);
        model.addAttribute("userRegistDTO", registDTO);
        String message = (String) session.getAttribute("errorMessage");
        model.addAttribute("errorMessage", message);
        session.removeAttribute("errorMessage");
    }


    @PostMapping("/customer/account/deleteUser")
    public String deleteUser() {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        userService.deletePeople(userCode);

        return "customer/res/res";

    }

    @GetMapping("/user/mainpage")
    public void mainpage(Model model) {

    }

    // 메인페이지 부품 수량 Js
    @PostMapping("/user/getpartchart")
    @ResponseBody
    public ChartResponseDTO getpartchart() {
        ChartResponseDTO chart = userService.getpartchart();

        return chart;
    }

    // 유저 통계
    @PostMapping("/user/getPersonChart")
    @ResponseBody
    public Map<String,Integer> getPersonChart() {
        Map<String,Integer> getPersonChart = userService.getpersonchart();

        return getPersonChart;
    }

    @GetMapping("/user/mypage")
    public void mypage(Model model) {

    }


    @PostMapping(value = "/user/mypage/changepass", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public int changepass(@RequestBody PasswordRequestDTO request, Model model) {
        String currentPassword = request.getCurrentPassword();
        String newPassword = request.getNewPassword();
        String pw = ((UserDTO) model.getAttribute("userDTO")).getPassword();
        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        int result = userService.changepass(currentPassword, newPassword, pw, userCode);

        return result;
    }


    @PostMapping("/user/mypage/update")
    public String updateUser(@RequestParam Map<String, String> myprofile) {

        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode(); // 기준으로

        myprofile.put("userCode", String.valueOf(userCode));
        userService.updateUser(myprofile);

        return "redirect:/user/mypage";
    }


    @GetMapping("/user/customermypage")
    public String customermypage(Model model, @RequestParam int customerUserCode, HttpSession session){

        int customerCode = customerUserCode / 123456 ;
        UserDTO userDTO = userService.findUserCode(customerCode);
        model.addAttribute("customerDTO",userDTO);
        session.setAttribute("customerUserCode",customerUserCode);
        return "user/anotherProfile";
    }

    @PostMapping("/user/customermypage/update")
    public String updateAnotherUser(@RequestParam Map<String, String> myprofile, HttpSession session) {


        int customerUserCode = (int) session.getAttribute("customerUserCode");
        if (customerUserCode != 0) {
            myprofile.put("userCode", String.valueOf(customerUserCode / 123456));
            userService.updateUser(myprofile);
            session.removeAttribute("customerUserCode");
        }
        return "redirect:/user/customermypage?customerUserCode=" + customerUserCode;
    }

    @PostMapping("/user/res/resTime")
    @ResponseBody
    public Map<String, Object> checkResTime(@RequestBody Map<String,Date> date) {

        System.out.println("fadsfasdfasdfas" + date.get("date"));
        Date date1 = date.get("date");
        List<String> time = userService.getTime(date1);
        // "disabledTimes": ["9", "11", "14"] 이대로 받으면 이거 비활성화 무조건 배열로 !!!!!!!!!

        System.out.println("time = " + time);
        Map<String,Object> disabledTimes = new HashMap();
//        List<String> disabledTimesList = Arrays.asList("9", "10", "11");

        disabledTimes.put("disabledTimes",time);


        return disabledTimes;
    }


    @PostMapping("/user/getCalendar")
    @ResponseBody
    public List<Map<String, Object>> getCalendar(Model model) {

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        List<Map<String, Object>> calendar = userService.getCalendar(userCode);
        System.out.println("calendar321312 = " + calendar);

        return calendar;
    }

}





