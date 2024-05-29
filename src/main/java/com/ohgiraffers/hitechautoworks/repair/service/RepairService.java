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
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RepairService {

    @Autowired
    private RepairMapper repairMapper;

    public List<Map<String,Object>> findAllRepair() {


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

    public List<Map<String,Object>> findWorkerList(Object code) {
        return repairMapper.findWorkerList(code);
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

    public List<Map<String, Object>> ModalClick(Object resCode) {

        return repairMapper.modalClick(resCode);
    }

    public void registPartStock(List<Map<String, Object>> info) {
        for (Map<String, Object> part : info) {
            Object partCode = part.get("part_code");
            int partCodeint = Integer.parseInt(partCode.toString());
            System.out.println("partCodeint = " + partCodeint);
            Object originalStock = repairMapper.selectPartStock(partCodeint);

            Object stock = part.get("stock");

            int originalStockint = Integer.parseInt(originalStock.toString());
            int stockint = Integer.parseInt(stock.toString());

            System.out.println("originalStockint = " + originalStockint);
            System.out.println("stockint = " + stockint);
            int modifyStock = originalStockint - stockint;
            System.out.println("modifyStock = " + modifyStock);
            repairMapper.modifyPartStock(partCodeint,modifyStock);
            Object resCode = part.get("resCode");
            System.out.println("resCode = " + resCode);
            repairMapper.modifyStatus(resCode);

        }

    }

    public int[] repairChart() {
        int[] chart = new int[12];
        for (int i = 1; i <=12; i++) {
            int count = repairMapper.repairChart(i);
            chart[i-1] = count;
        }
        System.out.println(Arrays.toString(chart));
        return chart;
        
    }

    public List<Map<String, Object>> workerChart() {
        return repairMapper.workerChart();
    }

    public List<Map<String, Object>> searchAllRepairComments(int resCode) {
        return repairMapper.searchAllRepairComments(resCode);
    }

    public int registComment(Map<String, Object> comment) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        comment.put("repairTime",formattedNow);
        return repairMapper.registComment(comment);
    }

    public int editComment(Map<String, Object> info) {
        return repairMapper.editComment(info);
    }

    public int deletComment(Map<String, Object> info) {
        return repairMapper.deleteComment(info);
    }

    public List<Map<String, Object>> searchAllReplyComments(int resCode) {
        return repairMapper.searchAllReplyComments(resCode);
    }

    public int submitRepairReply(Map<String, Object> info) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        info.put("repairTime",formattedNow);
        return repairMapper.submitRepairReply(info);
    }

    public Map<String, Object> searchRepairReply(Object replyCode) {
        return repairMapper.searchRepairReply(replyCode);
    }

    public int editRepairReplyComment(Map<String, Object> info) {
        return repairMapper.editRepairReplyComment(info);
    }

    public int deleteRepairReplyCommen(Map<String, Object> info) {
        return repairMapper.deleteRepairReplyCommen(info);
    }
}
