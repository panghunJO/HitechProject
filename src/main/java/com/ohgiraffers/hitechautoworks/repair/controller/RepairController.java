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
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class RepairController {

    @Autowired
    private RepairService repairService;

    @Autowired
    private UserService userService;

    private AuthUserInfo authUserInfo;

    @GetMapping("/user/repair")
    public void repair(Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        System.out.println("userDTO1 = " + userDTO1);
        model.addAttribute("userDTO", userDTO1);
        List<RepairDTO> repairList = repairService.findAllRepair();
        System.out.println("repairList = " + repairList);
        model.addAttribute("repairList", repairList);

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
            List<RepairDTO> repairList = repairService.findAllRepair();
            model.addAttribute("repairList", repairList);
            System.out.println("repairList = " + repairList);
        } else if (worker == "") {
            List<RepairDTO> repairList = repairService.SearchByworkerCode(Integer.parseInt(workerCode));
            System.out.println("repairList = " + repairList);
            model.addAttribute("repairList", repairList);
        } else {
            List<RepairDTO> repairList = repairService.SearchByworkerName(worker);
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
            @RequestParam String[] userName,
            @RequestParam String content,
            @RequestParam String[] partName,
            @RequestParam String status,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {


        List<String> userName1 = Arrays.asList(userName);
        List<String> partName1 = Arrays.asList(partName);
        repairService.modifyRepair(resCode, content, status, date);
        repairService.modifyRepairWorker(userName1, resCode);
        repairService.modifyRepairPart(partName1, resCode);
        return "redirect:/employee/repair/repair";
    }

    @PostMapping("/user/repairDelete")
    public String deleteRepair(@RequestParam int resCode) {

            repairService.deleteRepair(resCode);

        return"redirect:/employee/repair/repair";
    }
    @GetMapping("/employee/repair/repairAdd")
    public void partAdd(Model model){
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        authUserInfo.getUserDTO().getUserCode();
        String userName = userDTO.getUserName();
        model.addAttribute("userName",userName);
    }
    @GetMapping(value = "/employee/repair/part", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PartDTO> findPartList(){
        List<PartDTO> partList = repairService.findPartList();
        System.out.println("partList = " + partList);
        return partList;
    }
    @GetMapping(value = "/employee/repair/worker",produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<UserDTO> findWorkerList(){
        List<UserDTO> workerList = repairService.findWorkerList();
        System.out.println("workerList = " + workerList);
        return workerList;
    }
    @GetMapping(value ="/employee/repair/res" ,produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<ResDTO> findResList(){
        List<ResDTO> resList = repairService.findResList();
        System.out.println("resList = " + resList);
        return resList;
    }

    @PostMapping("/employee/repair/repairAdd")
    public String addRepair(@RequestParam int resCode,
                            @RequestParam String[] userName,
                            @RequestParam String content,
                            @RequestParam String[] partName,
                            @RequestParam String status,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        System.out.println("resCode = " + resCode);
        System.out.println("userName = " + userName);
        System.out.println("content = " + content);
        System.out.println("partName = " + partName);
        System.out.println("status = " + status);
        System.out.println("status = " + status);
        System.out.println("date = " + date);
        List<String> userName1 = Arrays.asList(userName);
        List<String> partName1 = Arrays.asList(partName);
        System.out.println("partName1 = " + partName1);
        System.out.println("userName1 = " + userName1);
        repairService.addRepair(resCode,content,status,date);
        repairService.addRepairPart(partName1,resCode);
        repairService.addRepairWorker(userName1,resCode);
        return "redirect:/employee/repair/repair";
    }

}
