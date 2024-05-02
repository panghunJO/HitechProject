// UserMapper.java

package com.ohgiraffers.hitechautoworks.auth.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    String overlappedID(String id);

    int regist(UserRegistDTO registDTO);

    UserDTO findByUsername(String username);

//    Integer findcheck(int checknumber, int userCode);

    Integer findcheck(@Param("checknumber") int checknumber, @Param("userCode") int userCode);
}
