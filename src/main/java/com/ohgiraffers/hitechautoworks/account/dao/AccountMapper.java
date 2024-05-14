package com.ohgiraffers.hitechautoworks.account.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AccountMapper {
    List<UserDTO> findAllUser();

    List<UserDTO> findUserCode(String userCode);

    List<UserDTO> findUserName(String userName);

    UserDTO selectAccount(int userCode);

    void updateUser(String userId, int userCode, String userDepartment, String userPw, String userEmail, String userPw1, String userAddress, String userPhone, String userName);

    void deleteUser(int userCode);
}
