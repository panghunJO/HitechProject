package com.ohgiraffers.hitechautoworks.res.dto;

public class EditCommentDTO {
    private String str;
    private String username;

    // getters and setters
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "EditCommentDTO{" +
                "str='" + str + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
