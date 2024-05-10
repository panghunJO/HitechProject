package com.ohgiraffers.hitechautoworks.part.dao;

import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PartMapper {

    List<PartDTO> selectPartByCode(int partCode);

    List<PartDTO> selectAllPart();

    PartDTO selectpart(int partCode);

    List<PartDTO> partSearchBtPartName(String partName);

    void modifyPart(String partCode, int partstock, String partName, int partPrice);

    void addPart(int partstock, String partName, int partPrice);

    void deletePart(String partCode);

}
