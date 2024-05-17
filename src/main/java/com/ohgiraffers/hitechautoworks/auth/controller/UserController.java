package com.ohgiraffers.hitechautoworks.auth.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private AuthUserInfo authUserInfo;

    @Autowired
    private UserService userService;

    @Autowired
    private ResService resService;

    @Autowired
    private PartService partService;


    @GetMapping("/user/dashboard")
    public void dashboard(Model model) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName", userName);
    }

    @GetMapping("/customer/dashboard")
    public void dashboard2(Model model) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName", userName);
    }

    @GetMapping("/employee/dashboard")
    public void employee(Model model) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
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
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        System.out.println("userDTO1 = " + userDTO1);
        model.addAttribute("userDTO", userDTO1);
    }

    @GetMapping("/user/mypage")
    public void mypage(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        System.out.println("userDTO1 = " + userDTO1);
        model.addAttribute("userDTO", userDTO1);
        System.out.println("userDTO = " + userDTO);
    }


    @PostMapping(value = "/user/mypage/changepass", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String changepass(@RequestBody PasswordRequestDTO request) {
        System.out.println("request = " + request);
        String currentPassword = request.getCurrentPassword();
        String newPassword = request.getNewPassword();
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        String pw = userDTO1.getPassword();
        String result = String.valueOf(userService.changepass(currentPassword, newPassword, pw, userCode));


        return result;
    }



    @GetMapping("/user/common")
    public void common(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        System.out.println("userDTO1 = " + userDTO1);
        model.addAttribute("userDTO", userDTO1);
        List<ResDTO> resList = resService.findAllres();
        model.addAttribute("resList", resList);
    }


    @PostMapping("/user/res/ressearch")
    public String resgo(Model model,@RequestParam String resCode, @RequestParam String resName){
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        System.out.println("userDTO1 = " + userDTO1);
        model.addAttribute("userDTO", userDTO1);

        System.out.println("resCode = " + resCode);
        System.out.println("resName = " + resName);
        if(resCode != "" && resName == ""){
            int resCodeInt = Integer.parseInt(resCode);
            ResDTO resList = resService.findUserRes(resCodeInt);
            model.addAttribute("resList", resList);
        } else if (resCode == "" && resName != ""){
            List<ResDTO> resList = resService.findNameRes(resName);
            model.addAttribute("resList", resList);
        } else {
            List<ResDTO> resList = resService.findAllres();
            model.addAttribute("resList", resList);
        }

        return "user/common";
    }

    @GetMapping("/user/partAllCall")
    public void partAllCall(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);

        List<PartDTO> partList = partService.selectAllPart();
        model.addAttribute("partList", partList);

    }

    @PostMapping("/user/partAllCall")
    public String partAllCall2(@RequestParam String partName, @RequestParam String partCode,
                               Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);

        if (partName == "" && partCode == "") {
            List<PartDTO> partList = partService.selectAllPart();
            model.addAttribute("partList", partList);
            System.out.println("partList = " + partList);
        } else if (partName == "") {
            List<PartDTO> partList = partService.selectPartByCode(Integer.parseInt(partCode));
            System.out.println("partList = " + partList);
            model.addAttribute("partList", partList);
        } else {
            List<PartDTO> partList = partService.partSearchBtPartName(partName);
            System.out.println("partList = " + partList);
            model.addAttribute("partList", partList);
        }

        return "user/partAllCall";
    }

    @PostMapping("/user/registpart")
    public String registpart(@RequestParam Map<String, String> parts, Model model){
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);

        int result = partService.addPart(parts);

        if(result == 1) {
            String partName = parts.get("partName");
            model.addAttribute("result", result);
            model.addAttribute("partName", partName);
        }

        return "user/partAllCall";
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
    @GetMapping("/user/partAdd")
    public void partAdd(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);
    }

    @GetMapping("/user/testPage")
    public void testpage(Model model,@RequestParam int resCode,HttpSession session) {

        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);

            ResDTO res = resService.findUserRes(resCode/123456);        // 들어올때 resCode 123456 나눠줘야댐 (나중에 제대로 암호화 ㄱㄱ)
            model.addAttribute("res", res);
            model.addAttribute("sqldate",res.getSqlDate());
            List<ResCommentDTO> resCommentDTO = resService.findComment(resCode/123456);
            model.addAttribute("resComment",resCommentDTO);

        if(session.getAttribute("result") != null){
            model.addAttribute("result", session.getAttribute("result"));
            session.removeAttribute("result");
        }

    }

    @PostMapping("/user/registcomment")
    public String testPage2(@RequestParam String comment, @RequestParam int resCode) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        resService.registcomment(comment,resCode,userCode);
        return "redirect:/user/testPage?resCode=" + 123456 * resCode;
    }

    @PostMapping("/user/resUpdate")
    public String resModify(@RequestParam int resCode ,@RequestParam String fixOption,@RequestParam String date,@RequestParam String extra, Model model,
                            HttpSession session){
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);

        int result = resService.resModify(resCode,fixOption,date,extra);
        if (result == 1){
            session.setAttribute("result",result);
        }
        return "redirect:/user/testPage?resCode=" + resCode;
    }

    @PostMapping("/user/resDelete")
    public String resDelete(@RequestParam int resCode){
        resService.resDelete(resCode);

        return "user/testPage";
    }


    @GetMapping("/user/rescustomer")
    public void resccustomer(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);
        List<ResDTO> resList = resService.findCustomerRes(userCode);
        model.addAttribute("resList",resList);

    }


    @PostMapping("/user/reseditComment")
    @ResponseBody
    public int editComment(@RequestBody EditCommentDTO editCommentDTO){
        int resReplyCode = editCommentDTO.getResReplyCode();
        String editcomment = editCommentDTO.getStr();
        int rescode = editCommentDTO.getRescode();

        int result = resService.updateComment(resReplyCode, editcomment);
        System.out.println("result = " + result);

//        return "redirect:/customer/res/resDetail?resCode=" + rescode;
        return result;
    }

    @PostMapping("/user/deleteComment")
    @ResponseBody
    public int deleteComment(@RequestBody DeleteCommentDTO deleteCommentDTO){
        int resReplyCode = deleteCommentDTO.getResReplyCode();
        int rescode = deleteCommentDTO.getRescode();
        System.out.println("deleteCommentDTO111 = " + deleteCommentDTO);
        int result = resService.deleteComment(resReplyCode);

        return result;

    }



}





