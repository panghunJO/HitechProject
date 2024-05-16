// UserService.java

package com.ohgiraffers.hitechautoworks.auth.service;

import com.ohgiraffers.hitechautoworks.auth.dao.UserMapper;
import com.ohgiraffers.hitechautoworks.auth.dto.*;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;



    public String overlappedID(String id) {
        String result = userMapper.overlappedID(id);
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



    public UserRegistDTO getAll(int userCode) {
        return userMapper.getAll(userCode);
    }

    public void deletePeople(int userCode) {
        userMapper.deletePeople(userCode);
    }

    public UserDTO findUserCode(int userCode) {
        return userMapper.findUserCode(userCode);
    }

    public int changepass(String currentPassword, String newPassword, String pw, int userCode) {
        if(passwordEncoder.matches(currentPassword,pw)) {
            String encodepw = passwordEncoder.encode(newPassword);
            System.out.println("비밀번호 일치");
            userMapper.changepass(encodepw,userCode);
            return 1;
        } else {
            System.out.println("불일치");
            return 0;
        }
    }

    public void updateUser(Map<String, String> myprofile) {
        userMapper.updateUser(myprofile);
    }



}
