package com.ohgiraffers.hitechautoworks.auth.controller;


import com.ohgiraffers.hitechautoworks.auth.dto.*;

import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.part.service.PartService;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.*;

@Controller
@SessionAttributes("userDTO")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private PartService partService;


    @ModelAttribute
    public void addUserToModel(Model model){
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO",userDTO1);
    }


    @GetMapping("/user/mainpage")
    public String mainpage(Model model) {

        return "user/mainpage";
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

    @GetMapping("/mypage")
    public String mypage(Model model) {


        return "user/mypage";
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
    public String updateUser(@RequestParam Map<String, Object> myprofile) {

        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode(); // 기준으로

        myprofile.put("userCode", String.valueOf(userCode));
        userService.updateUser(myprofile);

        return "redirect:/mypage";
    }



    @GetMapping("/showprofile")
    public String customermypage(Model model, @RequestParam int customerUserCode, HttpSession session){

        int customerCode = customerUserCode / 123456 ;
        UserDTO userDTO = userService.findUserCode(customerCode);
        model.addAttribute("customerDTO",userDTO);
        session.setAttribute("customerUserCode",customerUserCode);
        return "user/anotherProfile";
    }

    @PostMapping("/user/customermypage/update")
    public String updateAnotherUser(@RequestParam Map<String, Object> myprofile, HttpSession session) {


        int customerUserCode = (int) session.getAttribute("customerUserCode");
        if (customerUserCode != 0) {
            myprofile.put("userCode", String.valueOf(customerUserCode / 123456));
            userService.updateUser(myprofile);
            session.removeAttribute("customerUserCode");
        }
        return "redirect:/showprofile?customerUserCode=" + customerUserCode;
    }


    @PostMapping("/user/getCalendar")
    @ResponseBody
    public List<Map<String, Object>> getCalendar(Model model) {

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        List<Map<String, Object>> calendar = userService.getCalendar(userCode);

        return calendar;
    }

    @PostMapping("/user/submitReply")
    @ResponseBody
    public Map<String,Object> submitReply(@RequestBody Map<String,Object> info, Model model) {

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        int resReplyCode = (int) info.get("replyCode");
        info.put("userCode",userCode);
        int result = userService.submitReply(info);

        Map<String,Object> commentInfo = userService.getReplyComment(resReplyCode);


        return commentInfo;
    }


    @GetMapping("/user/contactList")
    public void contactList(Model model){

        List<ContactDTO> contactList = userService.contactList();

        model.addAttribute("contactList",contactList);

    }

    @GetMapping("/user/contactdetail")
    public String contactdetail(@RequestParam int contactCode ,Model model){
        String status = userService.findContactStatus(contactCode);
        if(status.equals("신규")) {
            userService.changeContact(contactCode);
        }
        ContactDTO contact = userService.selectContact(contactCode);
        model.addAttribute("contact",contact);

        return "user/contactdetail";
    }

    @PostMapping("/user/deletecontact")
    public String deleteContact(@RequestParam int contactCode,Model model){
       userService.deleteContact(contactCode);
       return "redirect:/user/contactList";
    }

    @PostMapping("/user/contactSearch")
    public String contactSearch(@RequestParam String contactCode ,@RequestParam String userName,Model model){

        if (contactCode == "" && userName == "") {
            List<ContactDTO> contactList = userService.contactList();
            model.addAttribute("contactList", contactList);
        } else if (userName == "") {
            List<ContactDTO> contactList = userService.selectContactByCode(Integer.parseInt(contactCode));
            model.addAttribute("contactList", contactList);
        } else {
            List<ContactDTO> contactList = userService.selectContactByName(userName);
            model.addAttribute("contactList", contactList);
        }
        return "user/contactList";
    }


    @GetMapping("/contact")
    public String contact(Model model) {
        String userName = ((UserDTO) model.getAttribute("userDTO")).getUserName();
        String email = ((UserDTO) model.getAttribute("userDTO")).getUserEmail();
        model.addAttribute("userName",userName);
        model.addAttribute("email",email);
        return "user/contact";
    }


    @PostMapping("/user/changeStatus")
    public String changeStatus(@RequestParam int contactCode){
        userService.changeStatus(contactCode);

        return "redirect:/user/contactdetail?contactCode=" + contactCode;
    }

    @PostMapping("/user/submitContact")
    @ResponseBody
    public int submitContact(@RequestBody Map<String,Object> info, Model model ){
        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        info.put("userCode",userCode);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        info.put("nowTime",timestamp);
        int result = userService.submitContact(info);

        return result;
    }

    @PostMapping("/user/saveNote")
    @ResponseBody
    public void saveNote(@RequestBody Map<String,Object> info, Model model){
        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        info.put("userCode",userCode);
        userService.saveNote(info);
    }

    @GetMapping("/user/getNote")
    @ResponseBody
    public Map<String,Object> getNote() {

        Map<String,Object> note = userService.getNote();

        return note;
    }
    @GetMapping(value = "/user/repairnoti", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<RepairDTO> repairnoti( Model model){
        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        List<RepairDTO> repairList = userService.repairnoti(userCode);

        return repairList;
    }
    @GetMapping(value = "/user/partnoti",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PartDTO> partnoti( ){
        List<PartDTO> partList = userService.partnoti();
        return partList;
    }


    @PostMapping("/user/imgUpload")
    @ResponseBody
    public int imgUpload(@RequestParam("profileImage") MultipartFile file, Model model) {
        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();

        int result = userService.imgUpload(file, userCode);

        return result;
    }

    @GetMapping("/user/contactcustomer")
    public String contactcustomer(Model model){

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        List<ContactDTO> contactList =  userService.myContact(userCode);
        model.addAttribute("contactList",contactList);

        return "user/contactcustomer";
    }


}

