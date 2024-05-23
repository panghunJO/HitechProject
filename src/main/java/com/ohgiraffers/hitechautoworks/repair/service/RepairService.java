package com.ohgiraffers.hitechautoworks.repair.service;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import com.ohgiraffers.hitechautoworks.repair.dao.RepairMapper;
import com.ohgiraffers.hitechautoworks.repair.dto.Repair2DTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairPartDTO;
import com.ohgiraffers.hitechautoworks.repair.dto.WorkerDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RepairService {

    @Autowired
    private RepairMapper repairMapper;

    public List<Map<String,Object>>   findAllRepair() {
        return repairMapper.findAllRepair();
    }

    public List<Map<String,Object>>SearchByworkerCode(int workerCode) {
        return repairMapper.SearchByworkerCode(workerCode);
    }

    public List<Map<String,Object>> SearchByworkerName(String worker) {
        return repairMapper.SearchByworkerName(worker);
    }

    public RepairDTO selectRepair(int resCode) {
        return repairMapper.selectRepair(resCode);
    }

    public void modifyRepair(int resCode, String content, String status, String date) {
        repairMapper.modifyRepair(resCode, content, status,date);
    }

    public void modifyRepairWorker(List<String> userName, int resCode) {
        List<Integer> newUserCode = repairMapper.selectUserCodeByUserName(userName);
        repairMapper.deleteOldWorker(resCode);
        repairMapper.modifyRepairWorker(newUserCode, resCode);
    }

    public void modifyRepairPart(List<String> partName, int resCode) {
        List<Integer>  newPartCode = repairMapper.selectPartCodeByPartName(partName);
        repairMapper.deleteOldPart(resCode);
        repairMapper.modifyRepairPart(newPartCode, resCode);
    }

    public void deleteRepair(int resCode) {
        repairMapper.deleteRepair(resCode);
    }

    public List<PartDTO> findPartList() {
        return repairMapper.findPartList();
    }

    public List<UserDTO> findWorkerList() {
        return repairMapper.findWorkerList();
    }

    public void addRepair(int resCode, String content, String status, String date, int extraTime) {
        repairMapper.addRepair(resCode,content,status,date, extraTime);
    }

    public void addRepairPart(List<String> partName, int resCode) {
        System.out.println("partName = " + partName);
        List<Integer> newPartCode = repairMapper.selectPartCodeByPartName(partName);
        System.out.println("newPartCode = " + newPartCode);
        repairMapper.addRepairPart(newPartCode,resCode);
    }

    public void addRepairWorker(List<String> userName, int resCode) {
        System.out.println("userName = " + userName);
        List<Integer> newUserCode = repairMapper.selectUserCodeByUserName(userName);
        System.out.println("newUserCode = " + newUserCode);
        repairMapper.addRepairWorker(newUserCode,resCode);
    }

    public List<ResDTO> findResList() {
        return repairMapper.findResList();
    }

    public List<RepairPartDTO> selectRepairPart(int resCode) {
        return repairMapper.selectRepairPart(resCode);
    }

    public List<WorkerDTO> selectWorker(int resCode) {
        return repairMapper.selectWorker(resCode);
    }

    public Map<String, Object> getDate(Object code) { return repairMapper.getDate(code);
    }
}
