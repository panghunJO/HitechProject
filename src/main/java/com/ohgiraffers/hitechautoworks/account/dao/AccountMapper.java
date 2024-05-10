package com.ohgiraffers.hitechautoworks.account.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AccountMapper {
    List<UserDTO> findAllUser();

    List<UserDTO> findUserCode(String userCode);

    List<UserDTO> findUserName(String userName);
}
