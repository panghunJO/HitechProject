package com.ohgiraffers.hitechautoworks.res.dao;

import com.ohgiraffers.hitechautoworks.res.dto.ResCommentDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;
import com.ohgiraffers.hitechautoworks.res.dto.ResRegistDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ResMapper {
    List<ResDTO> findAllres();

    ResDTO findUserRes(int resCode);

    List<ResDTO> findCodeRes(int resCode);

    int registres(ResRegistDTO resRegistDTO);


    void registcomment(String comment, int rescode, Timestamp date, int usercode);


    List<ResCommentDTO> findComment(int resCode);

    void resModify(int resCode, String fixOption,String date, String extra);

    void resDelete(int resCode);

    void gofile(MultipartFile file);

    int updateComment(int resReplyCode, String editcomment);

    void deleteComment(int resReplyCode);
}
