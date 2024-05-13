package com.ohgiraffers.hitechautoworks.res.dto;

import java.sql.Timestamp;

public class ResCommentDTO {
    private String resReplyContent;
    private Timestamp resTime;
    private String commentName;

    public ResCommentDTO() {
    }

    public ResCommentDTO(String resReplyContent, Timestamp resTime, String commentName) {
        this.resReplyContent = resReplyContent;
        this.resTime = resTime;
        this.commentName = commentName;
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
                "resReplyContent='" + resReplyContent + '\'' +
                ", resTime=" + resTime +
                ", commentName='" + commentName + '\'' +
                '}';
    }
}
