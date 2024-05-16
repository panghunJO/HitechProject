package com.ohgiraffers.hitechautoworks.repair.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.Repair2DTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairPartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.WorkerDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface RepairMapper {

    List<RepairDTO> findAllRepair();
    List<RepairDTO> SearchByworkerCode(int workerCode);

    List<RepairDTO> SearchByworkerName(String worker);

    List<Repair2DTO> selectRepair(int resCode);

    void modifyRepair(int resCode, String content, String status, Date date);

    List<Integer>  selectUserCodeByUserName(List<String> userName);

    List<Integer> selectPartCodeByPartName(List<String> partName);

    void modifyRepairWorker(List<Integer>  newUserCode, int resCode);

    void modifyRepairPart(List<Integer>  newPartCode, int resCode);

    void deleteRepair(int resCode);

    List<PartDTO> findPartList();

    List<UserDTO> findWorkerList();

    List<ResDTO> findResList();

    void addRepair(int resCode, String content, String status, Date date);

    void addRepairPart(List<Integer>  newPartCode, int resCode);

    void addRepairWorker(List<Integer>  newUserCode, int resCode);

    List<RepairPartDTO> selectRepairPart(int resCode);

    List<WorkerDTO> selectWorker(int resCode);
}
