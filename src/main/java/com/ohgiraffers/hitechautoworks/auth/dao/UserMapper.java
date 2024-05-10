// UserMapper.java

package com.ohgiraffers.hitechautoworks.auth.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.*;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairPartDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    String overlappedID(String id);

    int regist(UserRegistDTO registDTO);

    UserDTO findByUsername(String username);

//    Integer findcheck(int checknumber, int userCode);

    void findcheck(@Param("checknumber") int checknumber, @Param("userId") String userId);



    List<RepairPartDTO> findRepairPart();
}
