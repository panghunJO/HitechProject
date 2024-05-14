package com.ohgiraffers.hitechautoworks.res.service;

import com.ohgiraffers.hitechautoworks.res.dao.ResMapper;
import com.ohgiraffers.hitechautoworks.res.dto.ResCommentDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResRegistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
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

    public List<ResDTO> findCodeRes(int resCode) {
        return resMapper.findCodeRes(resCode);
    }

    public int registres(ResRegistDTO resRegistDTO) {
        return resMapper.registres(resRegistDTO);
    }

    public void registcomment(String comment, int rescode, int usercode) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        resMapper.registcomment(comment, rescode, date, usercode);
    }

    public List<ResCommentDTO> findComment(int resCode) {
        return resMapper.findComment(resCode);
    }

    public void resModify(int resCode, String fixOption, String date, String extra) {
        resMapper.resModify(resCode, fixOption, date, extra);
    }

    public void resDelete(int resCode) {
        resMapper.resDelete(resCode);

    }

    public void gofile(MultipartFile file) {
        resMapper.gofile(file);
    }

    public int updateComment(int usercode, String editcomment) {
       return resMapper.updateComment(usercode,editcomment);
    }
}

