// UserService.java

package com.ohgiraffers.hitechautoworks.auth.service;

import com.ohgiraffers.hitechautoworks.auth.dao.UserMapper;
import com.ohgiraffers.hitechautoworks.auth.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Objects;

@Service
public class UserService  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

//    public List<RepairDTO> findRepairInfo() {
//
//
//        return userMapper.findRepairInfo();
//    }

    public String overlappedID(String id) {
        String result = userMapper.overlappedID(id);
        System.out.println("result = " + result);
        return result;
    }

    @Async
    public int regist(UserRegistDTO registDTO) {
        registDTO.setPass(passwordEncoder.encode(registDTO.getPass()));
        System.out.println("registDTO = " + registDTO);
        int result = 0;
        try {
            result = userMapper.regist(registDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public UserDTO findByUsername(String username) {

        UserDTO login = userMapper.findByUsername(username);

        if(!Objects.isNull(login)){
            return login;
        } else {
            return null;
        }
    }

    public int findcheck(int checknumber, String userId) {
        userMapper.findcheck(checknumber,userId);

        return 1;
    }

    public List<UserDTO> findAllUser() {

        return userMapper.findAllUser();

    }
    public List<PartDTO> selectPartByCode(int partCode) {
        List<PartDTO> partList = userMapper.selectPartByCode(partCode);

        return partList;
    }

    public List<PartDTO> selectAllPart() {
        List<PartDTO> partList = userMapper.selectAllPart();

        return partList;
    }

    public PartDTO selectpart(int partCode) {
        PartDTO partDTO = userMapper.selectpart(partCode);

        return partDTO;
    }

    public List<PartDTO> partSearchBtPartName(String partName) {

        List<PartDTO> partList = userMapper.partSearchBtPartName(partName);

        return partList;
    }


    public List<UserDTO> selectPartName(String partName) {
        return userMapper.selectPart(partName);
    }

    public List<UserDTO> findUserId(String userId) {
        List<UserDTO> userList = userMapper.findUserId(userId);
        return userList;
    }


    public List<UserDTO> findUserCode(String userCode) {
        List<UserDTO> userList2 = userMapper.findUserCode(userCode);
        return userList2;
    }


    public void modifyPart(String partCode, int partstock, int partPrice, String partName) {
        userMapper.modifyPart(partCode, partstock, partName, partPrice);
    }


    public void addPart(int partstock, int partPrice, String partName) {
        userMapper.addPart(partstock, partName, partPrice);
    }

    public ResDTO findUserRes(int resCode) {
        return userMapper.findUserRes(resCode);
    }

    public List<ResDTO> findAllres() {
        return userMapper.findAllres();
    }

}
