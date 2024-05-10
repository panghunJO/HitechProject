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

import java.util.List;
import java.util.Objects;

@Service
public class UserService  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;



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

    public void updateUser(String userId, String userPw, String userEmail,
                           String userPwCheck, String userAddress, String userPhone, String userName) {
        String pw12 = passwordEncoder.encode(userPw);
        userMapper.updateUser(userId,userName,userAddress,userEmail,userPhone,pw12);
    }


    public UserRegistDTO getAll(int userCode) {
        return userMapper.getAll(userCode);
    }

}
