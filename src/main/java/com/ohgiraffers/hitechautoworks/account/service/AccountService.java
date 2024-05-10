package com.ohgiraffers.hitechautoworks.account.service;

import com.ohgiraffers.hitechautoworks.account.dao.AccountMapper;
import com.ohgiraffers.hitechautoworks.auth.dao.UserMapper;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public List<UserDTO> findAllUser() {

        return accountMapper.findAllUser();

    }


    public List<UserDTO> findUserCode(String userCode) {
        List<UserDTO> userList = accountMapper.findUserCode(userCode);
        return userList;
    }


    public List<UserDTO> findUserName(String userName) {
        List<UserDTO> userList2 = accountMapper.findUserName(userName);
        return userList2;
    }
}
