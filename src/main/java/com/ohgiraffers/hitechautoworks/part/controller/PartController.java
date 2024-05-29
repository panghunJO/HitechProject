package com.ohgiraffers.hitechautoworks.part.controller;

import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.service.Details.AuthUserInfo;
import com.ohgiraffers.hitechautoworks.auth.service.UserService;
import com.ohgiraffers.hitechautoworks.part.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("userDTO")
public class PartController {

    @Autowired
    private PartService partService;

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


    @GetMapping("/user/partAllCall")
    public String partAllCall(Model model) {

        List<PartDTO> partList = partService.selectAllPart();
        model.addAttribute("partList", partList);

        return "user/partAllCall";
    }

    @PostMapping("/user/partAllCall")
    public String partAllCall2(@RequestParam String partName, @RequestParam String partCode,
                               Model model) {

        if (partName == "" && partCode == "") {
            List<PartDTO> partList = partService.selectAllPart();
            model.addAttribute("partList", partList);

        } else if (partName == "") {
            List<PartDTO> partList = partService.selectPartByCode(Integer.parseInt(partCode));
            model.addAttribute("partList", partList);
        } else {
            List<PartDTO> partList = partService.partSearchBtPartName(partName);
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


    @GetMapping("/user/partdetail")
    public String partdetail(@RequestParam int partCode,Model model) {


        PartDTO partDTO = partService.selectpart(partCode);
        model.addAttribute("partDTO", partDTO);

        return "user/partdetail";
    }

    @PostMapping("/user/partModify")
    public String partModify(@RequestParam int partCode,
                             @RequestParam String partName,
                             @RequestParam int partstock,
                             @RequestParam int partPrice){

     partService.modifyPart(partCode,partstock,partPrice,partName);

     return "redirect:/user/partdetail?partCode="+partCode;
    }

    @GetMapping("/user/partAdd")
    public void partAdd(Model model) {
    }

    @PostMapping("/user/partDelete")
    public String DeletePart(@RequestParam String partCode){

        partService.deletePart(partCode);

        return "redirect:/user/partAllCall";
    }



}
