package com.ohgiraffers.hitechautoworks.repair.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.Repair2DTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface RepairMapper {

    List<RepairDTO> findAllRepair();
    List<RepairDTO> SearchByworkerCode(int workerCode);

    List<RepairDTO> SearchByworkerName(String worker);

    Repair2DTO selectRepair(int resCode);

    void modifyRepair(int resCode, String content, String status, Date date);

    int selectUserCodeByUserName(String userName);

    int selectPartCodeByPartName(String partName);

    void modifyRepairWorker(int newUserCode, int resCode);

    void modifyRepairPart(int newPartCode, int resCode);

    void deleteRepair(int resCode);

    List<PartDTO> findPartList();

    List<UserDTO> findWorkerList();

    List<ResDTO> findResList();

    void addRepair(int resCode, String content, String status, Date date);

    void addRepairPart(int newPartCode, int resCode);

    void addRepairWorker(int newUserCode, int resCode);

}
