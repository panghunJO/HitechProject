package com.ohgiraffers.hitechautoworks.res.dto;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;

import java.sql.Timestamp;

public class ResCommentDTO {
    private UserDTO userDTO;
    private String resReplyContent;
    private Timestamp resTime;
    private String commentName;

    public ResCommentDTO() {
    }

    public ResCommentDTO(UserDTO userDTO, String resReplyContent, Timestamp resTime, String commentName) {
        this.userDTO = userDTO;
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
                ", resReplyContent='" + resReplyContent + '\'' +
                ", resTime=" + resTime +
                ", commentName='" + commentName + '\'' +
                '}';
    }
}
