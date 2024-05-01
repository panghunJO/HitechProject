package com.ohgiraffers.hitechautoworks.auth.service;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        UserDTO login = userService.findByUsername(username);

        if(Objects.isNull(login)){
            throw new UsernameNotFoundException("해당하는 회원정보가 존재하지 않습니다!!!");
        }

        return new AuthDetails(login);
    }
}
