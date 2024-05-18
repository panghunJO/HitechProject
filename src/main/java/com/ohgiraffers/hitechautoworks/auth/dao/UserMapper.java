// UserMapper.java

package com.ohgiraffers.hitechautoworks.auth.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.*;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairPartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    String overlappedID(String id);

    int regist(UserRegistDTO registDTO);

    UserDTO findByUsername(String username);

//    Integer findcheck(int checknumber, int userCode);

    void findcheck(@Param("checknumber") int checknumber, @Param("userId") String userId);






    UserRegistDTO getAll(int userCode);


    void deletePeople(int userCode);

    UserDTO findUserCode(int userCode);

    void changepass(String encodepw, int userCode);

    void updateUser(Map<String, String> myprofile);


    List<Map<String, Object>> getPartChart();

    List<Map<String, Integer>> getPersonChart();
}
