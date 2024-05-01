// UserService.java

package com.ohgiraffers.hitechautoworks.auth.service;

import com.ohgiraffers.hitechautoworks.auth.dao.UserMapper;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public int regist(UserRegistDTO registDTO) {
        registDTO.setPass(passwordEncoder.encode(registDTO.getPass()));
        System.out.println("registDTO = " + registDTO);
        int result = 0;
        try {
            result = userMapper.regist(registDTO);
        } catch (Exception e) {
            e.printStackTrace();        // 예외가 발생 했을 떄, 발생한 위치와 상태를 반환하는 메소드
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
}
