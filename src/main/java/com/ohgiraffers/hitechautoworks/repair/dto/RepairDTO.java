package com.ohgiraffers.hitechautoworks.repair.dto;

import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;

import java.util.Date;

public class RepairDTO {

    private String content;
    private Date date;
    private String status;
    private ResDTO resDTO;

    public RepairDTO() {}

    public RepairDTO(String content, Date date, String status, ResDTO resDTO) {
        this.content = content;
        this.date = date;
        this.status = status;
        this.resDTO = resDTO;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResDTO getResDTO() {
        return resDTO;
    }

    public void setResDTO(ResDTO resDTO) {
        this.resDTO = resDTO;
    }

    @Override
    public String toString() {
        return "RepairDTO{" +
                "content='" + content + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", resDTO=" + resDTO +
                '}';
    }
}

