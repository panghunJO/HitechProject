package com.ohgiraffers.hitechautoworks.part.dao;

import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PartMapper {

    List<PartDTO> selectPartByCode(int partCode);

    List<PartDTO> selectAllPart();

    PartDTO selectpart(int partCode);

    List<PartDTO> partSearchBtPartName(String partName);

    void modifyPart(int partCode, int partstock, String partName, int partPrice);

    void deletePart(String partCode);

    int addPart(Map<String, String> parts);
}
