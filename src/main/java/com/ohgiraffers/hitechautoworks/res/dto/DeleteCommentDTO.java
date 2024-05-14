package com.ohgiraffers.hitechautoworks.res.dto;

public class DeleteCommentDTO {
    private int resReplyCode;
    private int rescode;

    public int getResReplyCode() {
        return resReplyCode;
    }

    public void setResReplyCode(int resReplyCode) {
        this.resReplyCode = resReplyCode;
    }

    public int getRescode() {
        return rescode;
    }

    public void setRescode(int rescode) {
        this.rescode = rescode;
    }

    @Override
    public String toString() {
        return "DeleteCommentDTO{" +
                "resReplyCode=" + resReplyCode +
                ", rescode=" + rescode +
                '}';
    }
}
