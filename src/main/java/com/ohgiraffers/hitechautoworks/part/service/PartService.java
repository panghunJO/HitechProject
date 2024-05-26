package com.ohgiraffers.hitechautoworks.part.service;

import com.ohgiraffers.hitechautoworks.auth.dto.ContactDTO;
import com.ohgiraffers.hitechautoworks.part.dao.PartMapper;
import com.ohgiraffers.hitechautoworks.part.dto.PartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PartService {

    @Autowired
    private PartMapper partMapper;

    public List<PartDTO> selectPartByCode(int partCode) {
        List<PartDTO> partList = partMapper.selectPartByCode(partCode);

        return partList;
    }

    public List<PartDTO> selectAllPart() {
        List<PartDTO> partList = partMapper.selectAllPart();

        return partList;
    }

    public PartDTO selectpart(int partCode) {
        PartDTO partDTO = partMapper.selectpart(partCode);

        return partDTO;
    }

    public List<PartDTO> partSearchBtPartName(String partName) {

        List<PartDTO> partList = partMapper.partSearchBtPartName(partName);

        return partList;
    }
    public void modifyPart(String partCode, int partstock, int partPrice, String partName) {
        partMapper.modifyPart(partCode, partstock, partName, partPrice);
    }


    public void deletePart(String partCode) {
        partMapper.deletePart(partCode);
    }


    public int addPart(Map<String, String> parts) {
        return partMapper.addPart(parts);
    }


}
