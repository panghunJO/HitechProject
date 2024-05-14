package com.ohgiraffers.hitechautoworks.res.dto;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;

import java.sql.Timestamp;

public class ResCommentDTO {
    private UserDTO userDTO;
    private int resReplyCode;
    private int resCode;
    private String resReplyContent;
    private Timestamp resTime;
    private String commentName;

    public ResCommentDTO() {
    }

    public ResCommentDTO(UserDTO userDTO, int resReplyCode, int resCode, String resReplyContent, Timestamp resTime, String commentName) {
        this.userDTO = userDTO;
        this.resReplyCode = resReplyCode;
        this.resCode = resCode;
        this.resReplyContent = resReplyContent;
        this.resTime = resTime;
        this.commentName = commentName;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public int getResReplyCode() {
        return resReplyCode;
    }

    public void setResReplyCode(int resReplyCode) {
        this.resReplyCode = resReplyCode;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResReplyContent() {
        return resReplyContent;
    }

    public void setResReplyContent(String resReplyContent) {
        this.resReplyContent = resReplyContent;
    }

    public Timestamp getResTime() {
        return resTime;
    }

    public void setResTime(Timestamp resTime) {
        this.resTime = resTime;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    @Override
    public String toString() {
        return "ResCommentDTO{" +
                "userDTO=" + userDTO +
                ", resReplyCode=" + resReplyCode +
                ", resCode=" + resCode +
                ", resReplyContent='" + resReplyContent + '\'' +
                ", resTime=" + resTime +
                ", commentName='" + commentName + '\'' +
                '}';
    }
}
