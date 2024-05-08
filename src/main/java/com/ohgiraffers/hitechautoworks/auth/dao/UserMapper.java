// UserMapper.java

package com.ohgiraffers.hitechautoworks.auth.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.auth.dto.UserRegistDTO;
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


    List<UserDTO> selectPart(String UserName);

    List<UserDTO> findUserId(String userId);

    List<UserDTO> findUserCode(String userCode);

    void modifyPart(String partCode, int partstock, String partName, int partPrice);


    void addPart(int partstock, String partName, int partPrice);

    List<ResDTO> findAllres();

    ResDTO findUserRes(int resCode);



//    List<RepairDTO> findRepairInfo();
}
