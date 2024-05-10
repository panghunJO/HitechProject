package com.ohgiraffers.hitechautoworks.res.service;

import com.ohgiraffers.hitechautoworks.res.dao.ResMapper;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResRegistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResService {

    @Autowired
    private ResMapper resMapper;

    public ResDTO findUserRes(int resCode) {
        return resMapper.findUserRes(resCode);
    }

    public List<ResDTO> findAllres() {
        return resMapper.findAllres();
    }

    public List<ResDTO> findCodeRes(int resCode) { return resMapper.findCodeRes(resCode);
    }

    public int registres(ResRegistDTO resRegistDTO) {
        return resMapper.registres(resRegistDTO);
    }
}
