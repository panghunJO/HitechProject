// UserMapper.java

package com.ohgiraffers.hitechautoworks.auth.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.*;
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

    List<UserDTO> findAllUser();

    List<PartDTO> selectPartByCode(int partCode);

    List<PartDTO> selectAllPart();

    PartDTO selectpart(int partCode);

    List<PartDTO> partSearchBtPartName(String partName);

    List<ResDTO> findAllres();

    ResDTO findUserRes(int resCode);

//    List<RepairDTO> findRepairInfo();
}
