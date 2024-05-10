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

@Controller
public class PartController {

    @Autowired
    private PartService partService;

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

    @GetMapping("/employee/part/partAdd")
    public void partAdd(Model model){
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName",userName);
    }

    @PostMapping("/employee/part/partAdd")
    public String partAdd(@RequestParam String partName, @RequestParam int partstock, @RequestParam int partPrice){
        partService.addPart(partstock, partPrice, partName);
        return "/employee/part/part";
    }

    @GetMapping("/employee/part/partdetail")
    public void partdetail(@RequestParam int partCode,
                           Model model) {
        PartDTO partDTO = partService.selectpart(partCode);
        System.out.println("partDTO = " + partDTO);
        model.addAttribute("partDTO", partDTO);
        authUserInfo = new AuthUserInfo();
        UserDTO userDTO = authUserInfo.getUserDTO();
        String userName = userDTO.getUserName();
        model.addAttribute("userName",userName);
    }

    @PostMapping("/employee/part/partdetail")
    public String part(@RequestParam String partName, @RequestParam int partstock, @RequestParam int partPrice, @RequestParam String partCode ){
        partService.modifyPart(partCode, partstock, partPrice, partName);
        return "/employee/part/part";
    }

    @PostMapping("/employee/part/delete")
    public String delete(@RequestParam String partCode){
        partService.deletePart(partCode);

        return "/employee/part/part";
    }




}
