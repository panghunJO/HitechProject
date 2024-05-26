package com.ohgiraffers.hitechautoworks.auth.dto;

import java.sql.Timestamp;

public class ContactDTO {
    private int code;
    private String title;
    private String content;
    private Timestamp date;
    private UserDTO userDTO;
    public ContactDTO() {}

    public ContactDTO(int code, String title, String content, Timestamp date, UserDTO userDTO) {
        this.code = code;
        this.title = title;
        this.content = content;
        this.date = date;
        this.userDTO = userDTO;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", userDTO=" + userDTO +
                '}';
    }
}
