package com.ohgiraffers.hitechautoworks.repair.controller;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.Repair2DTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import com.ohgiraffers.hitechautoworks.repair.dto.WorkerDTO;
import com.ohgiraffers.hitechautoworks.repair.service.RepairService;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class RepairController {

    @Autowired
    private RepairService repairService;

    private AuthUserInfo authUserInfo;

    @GetMapping("/employee/repair/repair")
    public void repair(Model model) {
        List<RepairDTO> repairList = repairService.findAllRepair();
        System.out.println("repairList = " + repairList);
        model.addAttribute("repairList", repairList);


    }

    @PostMapping("/employee/repair/repair")
    public void repair2(@RequestParam String worker, @RequestParam String workerCode,
                        Model model) {
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
    }

    @GetMapping("/employee/repair/repairdetail")
    public void repairdetail(@RequestParam int resCode,
                             Model model) {
        System.out.println("resCode = " + resCode);
        Repair2DTO repairDTO = repairService.selectRepair(resCode);
        System.out.println("repairDTO = " + repairDTO);
        model.addAttribute("repairDTO", repairDTO);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName", userName);
    }

    @PostMapping("/employee/repair/repairdetail")
    public String modifyRepair(
            @RequestParam int resCode,
            @RequestParam String userName,
            @RequestParam String content,
            @RequestParam String partName,
            @RequestParam String status,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        repairService.modifyRepair(resCode, content, status, date);
        repairService.modifyRepairWorker(userName, resCode);
        repairService.modifyRepairPart(partName, resCode);
        return "redirect:/employee/repair/repair";
    }

    @PostMapping("/employee/repair/delete")
    public String deleteRepair(@RequestParam int resCode) {

            repairService.deleteRepair(resCode);

        return"redirect:/employee/repair/repair";
    }
    @GetMapping("/employee/repair/repairAdd")
    public void partAdd(Model model){
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
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
                            @RequestParam String userName,
                            @RequestParam String content,
                            @RequestParam String partName,
                            @RequestParam String status,
                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        System.out.println("resCode = " + resCode);
        System.out.println("userName = " + userName);
        System.out.println("content = " + content);
        System.out.println("partName = " + partName);
        System.out.println("status = " + status);
        System.out.println("status = " + status);
        System.out.println("date = " + date);
        repairService.addRepair(resCode,content,status,date);
        repairService.addRepairPart(partName,resCode);
        repairService.addRepairWorker(userName,resCode);
        return "redirect:/employee/repair/repair";
    }

}
