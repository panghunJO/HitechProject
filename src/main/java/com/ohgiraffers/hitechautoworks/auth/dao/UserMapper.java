// UserMapper.java

package com.ohgiraffers.hitechautoworks.auth.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    String overlappedID(String id);

    int regist(UserRegistDTO registDTO);

    UserDTO findByUsername(String username);
}
