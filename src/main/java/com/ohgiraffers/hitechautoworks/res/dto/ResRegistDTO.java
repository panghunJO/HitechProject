package com.ohgiraffers.hitechautoworks.res.dto;

import java.sql.Date;

public class ResRegistDTO {
    private String title;
    private int userCode;
    private String resoption;
    private Date date;
    private String detailinfo;

    public ResRegistDTO() {
    }

    public ResRegistDTO(String title, int userCode, String resoption, Date date, String detailinfo) {
        this.title = title;
        this.userCode = userCode;
        this.resoption = resoption;
        this.date = date;
        this.detailinfo = detailinfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getResoption() {
        return resoption;
    }

    public void setResoption(String resoption) {
        this.resoption = resoption;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetailinfo() {
        return detailinfo;
    }

    public void setDetailinfo(String detailinfo) {
        this.detailinfo = detailinfo;
    }

    @Override
    public String toString() {
        return "ResRegistDTO{" +
                "title='" + title + '\'' +
                ", userCode=" + userCode +
                ", resoption='" + resoption + '\'' +
                ", date=" + date +
                ", detailinfo='" + detailinfo + '\'' +
                '}';
    }
}
