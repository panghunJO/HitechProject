package com.ohgiraffers.hitechautoworks.repair.dto;

import com.ohgiraffers.hitechautoworks.res.dto.ResDTO;

import java.sql.Timestamp;
import java.util.Date;

public class RepairDTO {

    private String content;
    private Timestamp date;
    private String status;
    private ResDTO resDTO;
    private int time;

    public RepairDTO() {}

    public RepairDTO(String content, Timestamp date, String status,int time, ResDTO resDTO) {
        this.content = content;
        this.date = date;
        this.status = status;
        this.time = time;
        this.resDTO = resDTO;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        if (this.date != null) {
            return new Timestamp(this.date.getTime());
        }
        return null;
    }

    public void setDate(Timestamp date) {
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
                ", time=" + time +
                '}';
    }
}

