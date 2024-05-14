package com.ohgiraffers.hitechautoworks.res.dto;

public class EditCommentDTO {
    private String str;
    private int usercode;

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

    @Override
    public String toString() {
        return "EditCommentDTO{" +
                "str='" + str + '\'' +
                ", usercode=" + usercode +
                '}';
    }
}
