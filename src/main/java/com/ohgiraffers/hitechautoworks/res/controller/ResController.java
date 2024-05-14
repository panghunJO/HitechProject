package com.ohgiraffers.hitechautoworks.res.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.EtcCarDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.ImportantDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.NormalDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.SomoDTO;
import com.ohgiraffers.hitechautoworks.res.dto.EditCommentDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResCommentDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import com.ohgiraffers.hitechautoworks.res.dto.ResRegistDTO;
import com.ohgiraffers.hitechautoworks.res.service.ResService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ResController {


    @Autowired
    private UserService userService;

    @Autowired
    private ResService resService;

    private AuthUserInfo authUserInfo;

    @GetMapping("/employee/res/res")
    public String res(Model model, HttpSession session){
        List<ResDTO> resList = resService.findAllres();
        System.out.println("resList = " + resList);
        model.addAttribute("resList", resList);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName",userName);
        String message = (String) session.getAttribute("success");
        model.addAttribute("message",message);
        return "employee/res/res";
    }

    @PostMapping("/employee/res/resgo")
    public String resgo(Model model){
        List<ResDTO> resList = resService.findAllres();
        model.addAttribute("resList", resList);
        return "redirect:/employee/res/res";
    }
    @GetMapping("/customer/res/resDetail")
    public void resdetail(@RequestParam("resCode") int rescode, Model model){

        System.out.println("rescode = " + rescode);
        ResDTO res = resService.findUserRes(rescode);
        model.addAttribute("res", res);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        System.out.println("userDTO = " + userDTO);
        String userName = userDTO.getUserName();
        System.out.println("userName = " + userName);
        model.addAttribute("userName",userName);
        List<ResCommentDTO> resCommentDTO = resService.findComment(rescode);
        model.addAttribute("resComment",resCommentDTO);
        System.out.println("resCommentDTO = " + resCommentDTO);
    }
    @PostMapping("/employee/res/res")
    public void res1(@RequestParam int resCode, Model model){
        System.out.println("resCode3322 = " + resCode);
        List<ResDTO> resList = resService.findCodeRes(resCode);
        System.out.println("resList = " + resList);
        model.addAttribute("resList", resList);

    }

    @GetMapping("/customer/res/res_01")
    public void res01() {
    }

    @PostMapping("/customer/res/res_01")
    public void res011() {
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


        List<String> partNewList = partAllList.stream().distinct().collect(Collectors.toList());
        httpSession.setAttribute("partAllList", partAllList);
        model.addAttribute("partlist", partNewList);
    }

    @GetMapping("/customer/res/res_07")
    public void res07(Model model) {
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName",userName);
    }

    @PostMapping("/customer/res/res_07")
    public void res071(@RequestBody String date, HttpSession httpSession){
        System.out.println("date = " + date);
        //"date":"2024-05-13"
        JsonObject jsonObject = JsonParser.parseString(date).getAsJsonObject();
        String dateValue = jsonObject.get("date").getAsString();
        System.out.println("dateValue = " + dateValue);
        Date date1 = Date.valueOf(dateValue);
        System.out.println("date1 = " + date1);
        httpSession.setAttribute("date",date1);

    }

    @GetMapping("/customer/res/res_08")
    public void res08() {

    }

    @PostMapping("/customer/res/res_08")
    public String res081(@RequestParam String title,
                       @RequestParam String detailinfo,
                       HttpSession httpSession) {
        ResRegistDTO resRegistDTO = new ResRegistDTO();
        resRegistDTO.setTitle(title);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int usercode = userDTO.getUserCode();
        resRegistDTO.setUserCode(usercode);
        List<String> partAllList = (List<String>) httpSession.getAttribute("partAllList");
        StringBuilder sb = new StringBuilder();
        for(String list : partAllList){
            sb.append(list);
            sb.append(",");
        }
        String partlist = sb.toString();
        resRegistDTO.setResoption(partlist);
        Date date = (Date) httpSession.getAttribute("date");
        resRegistDTO.setDate(date);
        System.out.println("date = " + date);
        resRegistDTO.setDetailinfo(detailinfo);
        int result = resService.registres(resRegistDTO);
        if (result == 1){
            httpSession.setAttribute("success","예약 등록에 성공하였습니다.");
        }
        return "/customer/res/res";
    }


    @PostMapping("/customer/res/rescomment")
    public String rescomment(@RequestParam String comment, @RequestParam("resCode") int rescode){
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        resService.registcomment(comment,rescode,userCode);
        return "/customer/res/res";
    }

    @PostMapping("/customer/res/resUpdate")
    public String resModify(@RequestParam int resCode ,@RequestParam String fixOption,@RequestParam String date,@RequestParam String extra ){
        resService.resModify(resCode,fixOption,date,extra);

    return "/customer/res/res";

    }

    @PostMapping("/customer/res/resDelete")
    public String resDelete(@RequestParam int resCode ){
     resService.resDelete(resCode);

     return "/customer/res/res";
    }

    @PostMapping("/customer/res/editComment")
    public String editComment(@RequestBody EditCommentDTO editCommentDTO){
        int usercode = editCommentDTO.getUsercode();
        String editcomment = editCommentDTO.getStr();
        int rescode = editCommentDTO.getRescode();
        System.out.println("username = " + usercode);
        System.out.println("editcomment = " + editcomment);
        resService.updateComment(usercode, editcomment);

        return "/customer/res/resDetail?resCode=" + rescode;
    }

    // 일반회원 수리예약을 자기것만 조회!!
    @GetMapping("/customer/res/res")
    public void res(Model model){
        // 현재 로그인한 사용자의 정보 가져오기
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();

        // 해당 사용자의 리소스만 조회하기
        List<ResDTO> resList = resService.findResByCusCode(userCode);
        System.out.println("resList = " + resList);

        // 모델에 데이터 추가
        model.addAttribute("resList", resList);

//        String message = (String) session.getAttribute("success");
//        model.addAttribute("message", message);

//        return "redirect:/customer/res/res";
    }

}
