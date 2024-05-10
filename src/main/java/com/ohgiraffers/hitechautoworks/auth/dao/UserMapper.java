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


    List<UserDTO> selectPart(String UserName);

    List<UserDTO> findUserCode(String userCode);

    List<UserDTO> findUserName(String userName);

    void modifyPart(String partCode, int partstock, String partName, int partPrice);


    void addPart(int partstock, String partName, int partPrice);

    List<ResDTO> findAllres();

    ResDTO findUserRes(int resCode);

    void deletePart(String partCode);

    List<ResDTO> findCodeRes(int resCode);

    List<RepairDTO> findAllRepair();

    List<RepairPartDTO> findRepairPart();


    UserRegistDTO getAll(int userCode);

    void updateUser(String userId, String userName, String userAddress, String userEmail, String userPhone, String pw12);
}
