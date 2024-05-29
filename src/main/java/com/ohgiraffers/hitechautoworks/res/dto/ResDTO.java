package com.ohgiraffers.hitechautoworks.res.dto;

import com.ohgiraffers.hitechautoworks.auth.dto.UserDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;


public class ResDTO {

    private int code;
    private String title;
    private UserDTO userDTO;
    private String option;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp date;
    private String extra;
    private String resAble;

    public ResDTO() { }

    public ResDTO(int code, String title, UserDTO userDTO, String option, Timestamp date, String extra, String resAble) {
        this.code = code;
        this.title = title;
        this.userDTO = userDTO;
        this.option = option;
        this.date = date;
        this.extra = extra;
        this.resAble = resAble;
    }

    public Date getSqlDate() {
        if (this.date != null) {
            return new Date(this.date.getTime());
        }
        return null;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getResAble() {
        return resAble;
    }

    public void setResAble(String resAble) {
        this.resAble = resAble;
    }

    @Override
    public String toString() {
        return "ResDTO{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", userDTO=" + userDTO +
                ", option='" + option + '\'' +
                ", date=" + date +
                ", extra='" + extra + '\'' +
                ", resAble='" + resAble + '\'' +
                '}';
    }
}


