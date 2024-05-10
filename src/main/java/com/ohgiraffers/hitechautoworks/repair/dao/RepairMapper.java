package com.ohgiraffers.hitechautoworks.repair.dao;

import com.ohgiraffers.hitechautoworks.repair.dto.RepairDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RepairMapper {

    List<RepairDTO> findAllRepair();
}
