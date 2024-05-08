// UserService.java

package com.ohgiraffers.hitechautoworks.auth.service;

import com.ohgiraffers.hitechautoworks.auth.dao.UserMapper;
import com.ohgiraffers.hitechautoworks.auth.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
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
}
