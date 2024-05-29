package com.ohgiraffers.hitechautoworks.repair.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.Repair2DTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairPartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.WorkerDTO;
import com.ohgiraffers.hitechautoworks.repair.service.RepairService;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@SessionAttributes("userDTO")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private UserService userService;


    @ModelAttribute
    public void addUserToModel(Model model){
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO",userDTO1);
    }

    @GetMapping("/user/repair")
    public String repair(Model model) {

        List<Map<String,Object>> repairList = repairService.findAllRepair();
        model.addAttribute("repairList", repairList);
        return "user/repair";
    }

    @PostMapping("/user/repair/repairSearch")
    public String repair2(@RequestParam String worker, @RequestParam String workerCode,
                        Model model) {


        if (worker == "" && workerCode == "") {
            List<Map<String,Object>>   repairList = repairService.findAllRepair();
            model.addAttribute("repairList", repairList);
        } else if (worker == "") {
            List<Map<String,Object>> repairList = repairService.SearchByworkerCode(Integer.parseInt(workerCode));
            model.addAttribute("repairList", repairList);
        } else {
            List<Map<String,Object>> repairList = repairService.SearchByworkerName(worker);
            model.addAttribute("repairList", repairList);
        }
        return "user/repair";
    }

    @GetMapping("/user/repairdetail")
    public void repairdetail(@RequestParam int resCode,
                             Model model) {

        RepairDTO repairDTO = repairService.selectRepair(resCode);
        List<RepairPartDTO> parts = repairService.selectRepairPart(resCode);
        List<WorkerDTO> workers = repairService.selectWorker(resCode);
        model.addAttribute("repairDTO", repairDTO);
        model.addAttribute("parts",parts);
        model.addAttribute("workers",workers);

        List<Map<String,Object>> repairComments = repairService.searchAllRepairComments(resCode);
        model.addAttribute("repairComments",repairComments);
        List<Map<String,Object>> replyComments = repairService.searchAllReplyComments(resCode);
        model.addAttribute("replyComments",replyComments);

    }

    @PostMapping("/user/submitRepairReply")
    @ResponseBody
    public Map<String,Object> submitRepairReply(@RequestBody Map<String,Object> info, Model model) {

        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        info.put("userCode",userCode);

        int result = repairService.submitRepairReply(info);
        Map<String,Object> data = repairService.searchRepairReply(info.get("replyCode"));

        return data;
    }

    @PostMapping("/user/deleteRepairReplyComment")
    @ResponseBody
    public int deleteRepairReplyCommen(@RequestBody Map<String,Object> info, Model model) {

        int result = repairService.deleteRepairReplyCommen(info);

        return result;
    }

    @PostMapping("/user/editRepairReplyComment")
    @ResponseBody
    public int editRepairReplyComment(@RequestBody Map<String,Object> info ){

        int result = repairService.editRepairReplyComment(info);

        return result;
    }

    @PostMapping("/user/editComment")
    @ResponseBody
    public int editComment(@RequestBody Map<String,Object> info, Model model){

        int result = repairService.editComment(info);

        return result;
    }

    @PostMapping("/user/deleteRepairComment")
    @ResponseBody
    public int deleteComment(@RequestBody Map<String,Object> info){

        int result = repairService.deletComment(info);

        return result;
    }

    @PostMapping("/user/registerComment")
    @ResponseBody
    public int registerComment(@RequestBody Map<String,Object> comment, Model model) {
        int userCode = ((UserDTO) model.getAttribute("userDTO")).getUserCode();
        comment.put("userCode",userCode);
        int result = repairService.registComment(comment);
        System.out.println("result = " + result);
        return 1;
    }

    @PostMapping("/user/repairModalInfo")
    @ResponseBody
    public List<Map<String,Object>> ModalClick(@RequestBody Map<String, Object> info) {
        Object resCode = info.get("resCode");
        List <Map<String, Object>> partList = repairService.ModalClick(resCode);
        return partList;
    }


    @PostMapping("/user/repairModify")
    @ResponseBody
    public String modifyRepair(
            @RequestBody Map<String,Object> info,Model model) {

        List<String> workers = (List<String>) info.get("worker");
        List<String> parts = (List<String>) info.get("part");
        String content = (String) info.get("content");
        String date = (String) info.get("date");
        String status = (String) info.get("status");
        String resCodeString = (String) info.get("resCode");

        int resCode = Integer.parseInt(resCodeString);

        repairService.modifyRepair(resCode, content, status,date);
        repairService.modifyRepairWorker(workers, resCode);
        repairService.modifyRepairPart(parts, resCode);

        return "1";
    }
    @PostMapping("/user/registerStock")
    @ResponseBody
    public String registPartStock(@RequestBody List<Map<String, Object>> info){
        repairService.registPartStock(info);
        return "1";
    }

    @PostMapping("/user/repairDelete")
    public String deleteRepair(@RequestParam int resCode) {

            repairService.deleteRepair(resCode);

        return "redirect:/user/repair";
    }

    @GetMapping(value = "/employee/repair/part", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PartDTO> findPartList(){
        List<PartDTO> partList = repairService.findPartList();
        return partList;
    }
    @PostMapping(value = "/employee/repair/worker")
    @ResponseBody
    public List<Map<String,Object>> findWorkerList(@RequestBody Map<String,Object> info){
        Object code = info.get("code");

        List<Map<String,Object>> workerList =  repairService.findWorkerList(code);
        return workerList;
    }
    @GetMapping(value ="/employee/repair/res" ,produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ResDTO> findResList(){
        List<ResDTO> resList = repairService.findResList();
        return resList;
    }
    @PostMapping("/repair/getDate")
    @ResponseBody
    public Map<String,Object> getDate(@RequestBody Map<String,Object> info,Model model){
        Object code = info.get("code");
        Map<String,Object> date = repairService.getDate(code);
        return date;
    }

    @PostMapping("/user/repair/regist")
    @ResponseBody
    public String addRepair(@RequestBody Map<String,Object> info,Model model){

        List<String> workers = (List<String>) info.get("worker");
        List<String> parts = (List<String>) info.get("part");
        String content = (String) info.get("content");
        String date = (String) info.get("date");
        String status = (String) info.get("status");
        String resCodeString = (String) info.get("resCode");
        String extraTimeString = info.get("extraTime").toString();

        int resCode = Integer.parseInt(resCodeString);
        int extraTime = Integer.parseInt(extraTimeString);


        repairService.addRepair(resCode,content,status,date,extraTime);
        repairService.addRepairPart(parts,resCode);
        repairService.addRepairWorker(workers,resCode);
        return "1";
    }

    @PostMapping("/user/repairChart")
    @ResponseBody
    public int[] repairChart(){
        int[] count = repairService.repairChart();
        return count;
    }

    @PostMapping("/user/workerChart")
    @ResponseBody
    public List<Map<String,Object>> workerChart(){
        List<Map<String,Object>> workerList = repairService.workerChart();
        return workerList;
    }

}
