package com.ohgiraffers.hitechautoworks.res.dto;

public class EditCommentDTO {
    private String str;
    private int usercode;
    private int rescode;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getUsercode() {
        return usercode;
    }

    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    public int getRescode() {
        return rescode;
    }

    public void setRescode(int rescode) {
        this.rescode = rescode;
    }

    @Override
    public String toString() {
        return "EditCommentDTO{" +
                "str='" + str + '\'' +
                ", usercode=" + usercode +
                ", rescode=" + rescode +
                '}';
    }
}
