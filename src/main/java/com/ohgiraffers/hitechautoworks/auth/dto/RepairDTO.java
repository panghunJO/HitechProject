package com.ohgiraffers.hitechautoworks.auth.dto;

public class RepairDTO {

    private int content;
    private int date;
    private int status;
    private ResDTO resCode;

    public RepairDTO() {}

    public RepairDTO(int content, int date, int status, ResDTO resCode) {
        this.content = content;
        this.date = date;
        this.status = status;
        this.resCode = resCode;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ResDTO getResCode() {
        return resCode;
    }

    public void setResCode(ResDTO resCode) {
        this.resCode = resCode;
    }

    @Override
    public String toString() {
        return "RepairDTO{" +
                "content=" + content +
                ", date=" + date +
                ", status=" + status +
                ", resCode=" + resCode +
                '}';
    }

}

