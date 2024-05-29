package com.ohgiraffers.hitechautoworks.res.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("userDTO")
public class ResController {

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


    @GetMapping("/user/rescheck")
    public String common(Model model) {
        List<ResDTO> resList = resService.findAllres();
        model.addAttribute("resList", resList);
        return "user/rescheck";
    }

    @PostMapping("/user/res/ressearch")
    public String resgo(Model model, @RequestParam String resCode, @RequestParam String resName){

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

        return "user/rescheck";
    }

    @GetMapping("/resdetail")
    public String testpage(Model model, @RequestParam int resCode, HttpSession session) {

        ResDTO res = resService.findUserRes(resCode / 123456);// 들어올때 resCode 123456 나눠줘야댐 (나중에 제대로 암호화 ㄱㄱ)
        model.addAttribute("res", res);
        String date = String.valueOf(res.getDate());
        String repair = resService.findStatus(resCode/123456);
        if (repair == null){
            repair = "대기";
        }
        model.addAttribute("repair", repair);
        model.addAttribute("sqldate", date.substring(0,19));
        List<ResCommentDTO> resCommentDTO = resService.findComment(resCode / 123456);
        model.addAttribute("resComment", resCommentDTO);
        List<Map<String,Object>> replyComment = resService.replyComment(resCode / 123456);
        model.addAttribute("replyComment",replyComment);
        if (session.getAttribute("result") != null) {
            model.addAttribute("result", session.getAttribute("result"));
            session.removeAttribute("result");
        }

        return "user/resdetail";
    }

    @PostMapping("/user/registcomment")
    public String testPage2(@RequestParam String comment, @RequestParam int resCode,Model model) {

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        resService.registcomment(comment,resCode,userCode);
        return "redirect:/resdetail?resCode=" + 123456 * resCode;
    }

    @PostMapping("/user/resUpdate")
    public String resModify(@RequestParam int resCode ,@RequestParam String fixOption,@RequestParam String date,@RequestParam String extra, Model model,
                            HttpSession session){

        int result = resService.resModify(resCode,fixOption,date,extra);
        if (result == 1){
            session.setAttribute("result",result);
        }
        return "redirect:/resdetail?resCode=" + 123456 * resCode;
    }

    @PostMapping("/user/resDelete")
    public String resDelete(@RequestParam int resCode){

        resService.resDelete(resCode);

        return "redirect:/user/rescheck";
    }


    @GetMapping("/rescustomer")
    public String resccustomer(Model model) {

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        List<ResDTO> resList = resService.findCustomerRes(userCode);
        model.addAttribute("resList",resList);

        return "user/rescustomer";
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

    @PostMapping("/user/reseditComment1")
    @ResponseBody
    public int editComment1(@RequestBody Map<String,Object> info){

        int result = resService.updateReComment(info);



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

    @PostMapping("/user/deleteComment1")
    @ResponseBody
    public int deleteComment1(@RequestBody Map<String,Object> deleteInfo){

        int result = resService.deleteReComment(deleteInfo);

        return result;
    }

    @GetMapping("/res")
    public String res() {

        return "user/res";
    }

    @GetMapping("/selectRes")
    public String selectRes() {

        return "user/selectRes";
    }

    @PostMapping("/user/res/resTime")
    @ResponseBody
    public Map<String, Object> checkResTime(@RequestBody Map<String, Date> date) {


        Date date1 = date.get("date");
        List<String> time = userService.getTime(date1);
        // "disabledTimes": ["9", "11", "14"] 이대로 받으면 이거 비활성화 무조건 배열로 !!!!!!!!!


        Map<String,Object> disabledTimes = new HashMap();
//        List<String> disabledTimesList = Arrays.asList("9", "10", "11");

        disabledTimes.put("disabledTimes",time);


        return disabledTimes;
    }

    @PostMapping("/user/res/Submit")
    @ResponseBody
    public String resSubmit(@RequestBody Map<String,Object> info,Model model){
        String date = (String) info.get("date");
        String time = (String) info.get("selectedRadioValue");
        String dateTime = date+' '+time;


        String option = (String) info.get("message");
        String resExtra = (String) info.get("resExtra");
        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        resService.insertRes(userCode,option,dateTime,resExtra);

        return "1";
    }


    @GetMapping("/resCar")
    public String resCar() {

        return "user/resCar";
    }

    @PostMapping("/user/res/carSubmit")
    public String carSubmit(@RequestParam String inputMessage) {

        return "user/res";
    }

}
