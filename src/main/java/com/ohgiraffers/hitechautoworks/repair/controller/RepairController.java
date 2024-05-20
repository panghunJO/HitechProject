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
        List<Repair2DTO> repairDTO = repairService.selectRepair(resCode);
        System.out.println("repairDTO = " + repairDTO);
        List<RepairPartDTO> parts = repairService.selectRepairPart(resCode);
        System.out.println("parts = " + parts);
        List<WorkerDTO> workers = repairService.selectWorker(resCode);
        model.addAttribute("repairDTO", repairDTO);
        model.addAttribute("parts",parts);
        model.addAttribute("workers",workers);

    }

    @PostMapping("/user/repairModify")
    public String modifyRepair(
            @RequestParam int resCode,
            @RequestParam(name = "userName") String[] userNames,
            @RequestParam String content,
            @RequestParam(name = "partName") String[] partNames,
            @RequestParam String status,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        List<String> userNameList = Arrays.asList(userNames);
        List<String> partNameList = Arrays.asList(partNames);
        repairService.modifyRepair(resCode, content, status, date);
        repairService.modifyRepairWorker(userNameList, resCode);
        repairService.modifyRepairPart(partNameList, resCode);
        return "redirect:/user/repair";
    }

    @PostMapping("/user/repairDelete")
    public String deleteRepair(@RequestParam int resCode) {

            repairService.deleteRepair(resCode);

        return "redirect:/user/repair";
    }
//    @GetMapping("/employee/repair/repairAdd")
//    public void partAdd(Model model){
//        authUserInfo = new AuthUserInfo();
//        UserDTO userDTO = authUserInfo.getUserDTO();
//        authUserInfo.getUserDTO().getUserCode();
//        String userName = userDTO.getUserName();
//        model.addAttribute("userName",userName);
//    }
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

    @PostMapping("/user/repair/regist")
    public String addRepair(@RequestParam int resCode,
                            @RequestParam(name = "userName") String[] userNames,
                            @RequestParam String content,
                            @RequestParam(name = "partName") String[] partNames,
                            @RequestParam String status,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        List<String> userNameList = Arrays.asList(userNames);
        List<String> partNameList = Arrays.asList(partNames);
        repairService.addRepair(resCode,content,status,date);
        repairService.addRepairPart(partNameList,resCode);
        repairService.addRepairWorker(userNameList,resCode);
        return "redirect:/user/repair";
    }

}
