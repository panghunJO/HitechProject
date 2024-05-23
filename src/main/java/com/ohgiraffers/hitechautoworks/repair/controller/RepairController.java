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
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private UserService userService;

    private AuthUserInfo authUserInfo;

    @GetMapping("/user/repair")
    public String repair(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);
        List<Map<String,Object>> repairList = repairService.findAllRepair();
        System.out.println("repairList = " + repairList);
        model.addAttribute("repairList", repairList);
        return "/user/repair";
    }

    @PostMapping("/user/repair/repairSearch")
    public String repair2(@RequestParam String worker, @RequestParam String workerCode,
                        Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);
        System.out.println("userDTO1 = " + userDTO1);
        System.out.println("worker = " + worker);
        System.out.println("workerCode = " + workerCode);
        if (worker == "" && workerCode == "") {
            List<Map<String,Object>>   repairList = repairService.findAllRepair();
            model.addAttribute("repairList", repairList);
            System.out.println("repairList = " + repairList);
        } else if (worker == "") {
            List<Map<String,Object>> repairList = repairService.SearchByworkerCode(Integer.parseInt(workerCode));
            System.out.println("repairList = " + repairList);
            model.addAttribute("repairList", repairList);
        } else {
            List<Map<String,Object>> repairList = repairService.SearchByworkerName(worker);
            System.out.println("repairList = " + repairList);
            model.addAttribute("repairList", repairList);
        }
        return "user/repair";
    }

    @GetMapping("/user/repairdetail")
    public void repairdetail(@RequestParam int resCode,
                             Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);
        System.out.println("resCode = " + resCode);
        RepairDTO repairDTO = repairService.selectRepair(resCode);
        System.out.println("repairDTO = " + repairDTO);
        List<RepairPartDTO> parts = repairService.selectRepairPart(resCode);
        System.out.println("parts = " + parts);
        List<WorkerDTO> workers = repairService.selectWorker(resCode);
        model.addAttribute("repairDTO", repairDTO);
        model.addAttribute("parts",parts);
        model.addAttribute("workers",workers);

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
        System.out.println("resCode = " + resCode);

        repairService.modifyRepair(resCode, content, status,date);
        repairService.modifyRepairWorker(workers, resCode);
        repairService.modifyRepairPart(parts, resCode);

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
    @GetMapping(value = "/employee/repair/worker",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserDTO> findWorkerList(){
        List<UserDTO> workerList = repairService.findWorkerList();
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

//        resCode:resCode,
//                worker:worker,
//                part:part,
//                content:content,
//                date :date,
//                status:status,
//                extraTime:extraTime
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

}
