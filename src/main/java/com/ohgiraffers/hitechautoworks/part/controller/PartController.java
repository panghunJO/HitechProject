package com.ohgiraffers.hitechautoworks.part.controller;

import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import com.ohgiraffers.hitechautoworks.part.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PartController {

    @Autowired
    private PartService partService;

    @Autowired
    private UserService userService;

    private AuthUserInfo authUserInfo;

    @GetMapping("/employee/part/part")
    public void part(Model model) {
        List<PartDTO> partList = partService.selectAllPart();
        model.addAttribute("partList", partList);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        String userRole = String.valueOf(userDTO.getUserRole());
        model.addAttribute("userName",userName);
        model.addAttribute("userRole",userRole);
    }

    @PostMapping("/employee/part/part")
    public void part2(@RequestParam String partName, @RequestParam String partCode,
                      Model model) {
        System.out.println("partName = " + partName);
        System.out.println("partCode = " + partCode);
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
    }


    @GetMapping("/user/partAllCall")
    public void partAllCall(Model model) {

        List<PartDTO> partList = partService.selectAllPart();
        model.addAttribute("partList", partList);
    }
    @PostMapping("/user/partAllCall")
    public String partAllCall2(@RequestParam String partName, @RequestParam String partCode,
                               Model model) {

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

        int result = partService.addPart(parts);
        if(result == 1) {
            String partName = parts.get("partName");
            model.addAttribute("result", result);
            model.addAttribute("partName", partName);
        }

        return "redirect:/user/partAllCall";
    }
    @GetMapping("/user/partAdd")
    public void partAdd(Model model) {
    }


    @GetMapping("/user/partdetail")
    public void partdetail(@RequestParam int partCode,
                           Model model) {
        AuthUserInfo authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        int userCode = userDTO.getUserCode();
        UserDTO userDTO1 = userService.findUserCode(userCode);
        model.addAttribute("userDTO", userDTO1);
        PartDTO partDTO = partService.selectpart(partCode);
        System.out.println("partDTO = " + partDTO);
        model.addAttribute("partDTO", partDTO);

    }

    @PostMapping("/user/partModify")
    public String part(@RequestParam String partName, @RequestParam int partstock, @RequestParam int partPrice, @RequestParam String partCode ){
        partService.modifyPart(partCode, partstock, partPrice, partName);
        return "redirect:/user/partAllCall";
    }

    @PostMapping("/user/partDelete")
    public String delete(@RequestParam String partCode){
        partService.deletePart(partCode);

        return "redirect:/user/partAllCall";
    }




}
