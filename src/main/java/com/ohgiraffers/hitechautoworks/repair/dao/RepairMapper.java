package com.ohgiraffers.hitechautoworks.repair.dao;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.Repair2DTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairPartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.WorkerDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface RepairMapper {

    List<Map<String,Object>> findAllRepair();
    List<Map<String,Object>> SearchByworkerCode(int workerCode);

    List<Map<String,Object>> SearchByworkerName(String worker);

    List<Repair2DTO> selectRepair(int resCode);

    void modifyRepair(int resCode, String content, String status);

    List<Integer>  selectUserCodeByUserName(List<String> userName);

    List<Integer> selectPartCodeByPartName(List<String> partName);

    void modifyRepairWorker(List<Integer>  newUserCode, int resCode);

    void modifyRepairPart(List<Integer>  newPartCode, int resCode);

    void deleteRepair(int resCode);

    List<PartDTO> findPartList();

    List<UserDTO> findWorkerList();

    List<ResDTO> findResList();

    void addRepair(int resCode, String content, String status, LocalDateTime date, int extraTime);

    void addRepairPart(List<Integer>  newPartCode, int resCode);

    void addRepairWorker(List<Integer>  newUserCode, int resCode);

    List<RepairPartDTO> selectRepairPart(int resCode);

    List<WorkerDTO> selectWorker(int resCode);


    void deleteOldWorker( int resCode);

    void deleteOldPart( int resCode);
}
