package com.ohgiraffers.hitechautoworks.auth.dto;

public class ResDTO {

    private int code;
    private String title;
    private UserDTO userCode;
    private int option;
    private int date;
    private int extra;

    public ResDTO() { }

    public ResDTO(int code, String title, UserDTO userCode, int option, int date, int extra) {
        this.code = code;
        this.title = title;
        this.userCode = userCode;
        this.option = option;
        this.date = date;
        this.extra = extra;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getUserCode() {
        return userCode;
    }

    public void setUserCode(UserDTO userCode) {
        this.userCode = userCode;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getExtra() {
        return extra;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ResDTO{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", userCode=" + userCode +
                ", option=" + option +
                ", date=" + date +
                ", extra=" + extra +
                '}';
    }

}
