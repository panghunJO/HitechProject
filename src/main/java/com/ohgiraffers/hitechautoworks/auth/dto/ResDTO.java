package com.ohgiraffers.hitechautoworks.auth.dto;

import java.util.Date;

public class ResDTO {

    private int code;
    private String title;
    private UserDTO userDTO;
    private String option;
    private Date date;
    private String extra;

    public ResDTO() { }

    public ResDTO(int code, String title, UserDTO userDTO, String option, Date date, String extra) {
        this.code = code;
        this.title = title;
        this.userDTO = userDTO;
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "ResDTO{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", userDTO=" + userDTO +
                ", option=" + option +
                ", date=" + date +
                ", extra=" + extra +
                '}';
    }

}
