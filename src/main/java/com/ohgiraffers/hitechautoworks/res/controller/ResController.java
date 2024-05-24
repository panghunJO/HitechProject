package com.ohgiraffers.hitechautoworks.res.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.EtcCarDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.ImportantDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.NormalDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.resdto.SomoDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.res.dto.*;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
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
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ResController {


    @Autowired
    private UserService userService;

    @Autowired
    private ResService resService;

    private AuthUserInfo authUserInfo;

    @GetMapping("/customer/res/res")
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
        return "customer/res/res";
    }

    @PostMapping("/customer/res/resgo")
    public String resgo(Model model){
        List<ResDTO> resList = resService.findAllres();
        model.addAttribute("resList", resList);
        return "redirect:/customer/res/res";
    }
    @GetMapping("/customer/res/resDetail")
    public void resdetail(@RequestParam("resCode") int rescode, Model model){
        System.out.println("rescode = " + rescode);
        ResDTO res = resService.findUserRes(rescode);
        model.addAttribute("res", res);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        System.out.println("userName = " + userName);
        model.addAttribute("userName",userName);
        List<ResCommentDTO> resCommentDTO = resService.findComment(rescode);
        model.addAttribute("resComment",resCommentDTO);
        System.out.println("resCommentDTO = " + resCommentDTO);
    }
    @PostMapping("/customer/res/res")
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
        return "customer/res/res";
    }


    @PostMapping("/customer/res/rescomment")
    public String rescomment(@RequestParam String comment, @RequestParam("resCode") int rescode){
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        resService.registcomment(comment,rescode,userCode);
        return "redirect:/customer/res/resDetail?resCode=" + rescode;
    }

    @PostMapping("/customer/res/resUpdate")
    public String resModify(@RequestParam int resCode ,@RequestParam String fixOption,@RequestParam String date,@RequestParam String extra ){
        resService.resModify(resCode,fixOption,date,extra);

    return "customer/res/res";

    }



    @GetMapping("/customer/res/repair")
    public void resRepair(@RequestParam("resCode") int resCode,Model model) {
        ResDTO res = resService.resRepair(resCode);
        System.out.println("res = " + res);
        model.addAttribute("res", res);
    }

    @GetMapping("/user/common")
    public void common(Model model) {
        List<ResDTO> resList = resService.findAllres();
        model.addAttribute("resList", resList);
    }
    @PostMapping("/user/res/ressearch")
    public String resgo(Model model,@RequestParam String resCode, @RequestParam String resName){

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
    @GetMapping("/user/testPage")
    public void testpage(Model model, @RequestParam int resCode, HttpSession session) {

        ResDTO res = resService.findUserRes(resCode / 123456);// 들어올때 resCode 123456 나눠줘야댐 (나중에 제대로 암호화 ㄱㄱ)
        model.addAttribute("res", res);
        String date = String.valueOf(res.getDate());
        String repair = resService.findStatus(resCode/123456);
        if (repair == null){
            repair = "대기";
        }
        model.addAttribute("repair", repair);
        model.addAttribute("sqldate", date.substring(0,19));
//        List<ResCommentDTO> resCommentDTO = resService.findComment(resCode / 123456);
//        model.addAttribute("resComment", resCommentDTO);

        if (session.getAttribute("result") != null) {
            model.addAttribute("result", session.getAttribute("result"));
            session.removeAttribute("result");
        }

    }
    @PostMapping("/user/registcomment")
    public String testPage2(@RequestParam String comment, @RequestParam int resCode,Model model) {

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        resService.registcomment(comment,resCode,userCode);
        return "redirect:/user/testPage?resCode=" + 123456 * resCode;
    }

    @PostMapping("/user/resUpdate")
    public String resModify(@RequestParam int resCode ,@RequestParam String fixOption,@RequestParam String date,@RequestParam String extra, Model model,
                            HttpSession session){

        int result = resService.resModify(resCode,fixOption,date,extra);
        if (result == 1){
            session.setAttribute("result",result);
        }
        return "redirect:/user/testPage?resCode=" + 123456 * resCode;
    }

    @PostMapping("/user/resDelete")
    public String resDelete(@RequestParam int resCode){
        resService.resDelete(resCode);

        return "user/testPage";
    }


    @GetMapping("/user/rescustomer")
    public void resccustomer(Model model) {

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
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

        return result;
    }
    @PostMapping("/user/deleteComment")
    @ResponseBody
    public int deleteComment(@RequestBody DeleteCommentDTO deleteCommentDTO){
        int resReplyCode = deleteCommentDTO.getResReplyCode();
        int rescode = deleteCommentDTO.getRescode();
        int result = resService.deleteComment(resReplyCode);

        return result;
    }
    @GetMapping("/user/res")
    public String res() {

        return "user/res";
    }

    @GetMapping("/user/selectRes")
    public String selectRes() {

        return "user/selectRes";
    }
    @PostMapping("/user/res/Submit")
    @ResponseBody
    public String resSubmit(@RequestBody Map<String,Object> info, Model model){
        String date = (String) info.get("date");
        String time = (String) info.get("selectedRadioValue");
        String dateTime = date+' '+time;
        System.out.println("dateTime = " + dateTime);
        for(String key : info.keySet()) {
            String value = (String) info.get(key);
            System.out.println(key + " : " + value);
        }
        String option = (String) info.get("message");
        String resExtra = (String) info.get("resExtra");
        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        resService.insertRes(userCode,option,dateTime,resExtra);

        return "1";
    }
    @GetMapping("/user/resCar")
    public String resCar() {

        return "user/resCar";
    }

    @PostMapping("/user/res/carSubmit")
    public String carSubmit(@RequestParam String inputMessage) {

        System.out.println("inputMessage = " + inputMessage);

        return "user/res";
    }

}
