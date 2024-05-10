package com.ohgiraffers.hitechautoworks.repair.service;

import com.ohgiraffers.hitechautoworks.repair.dao.RepairMapper;
import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RepairService {

    @Autowired
    private RepairMapper repairMapper;

    public List<RepairDTO> findAllRepair() {
        return repairMapper.findAllRepair();
    }
    //    public List<RepairDTO> findRepairInfo() {
//
//
//        return repairMapper.findRepairInfo();
//    }
}
