package com.ohgiraffers.hitechautoworks.auth.dto;

import com.ohgiraffers.hitechautoworks.common.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDTO {
    private int userCode;
    private String userId;
    private String password;
    private String userName;
    private UserRole userRole;

    public UserDTO() {
    }

    public UserDTO(int userCode, String userId, String password, String userName, UserRole userRole) {
        this.userCode = userCode;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userRole = userRole;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<String> getRole() {

        if(this.userRole.getRole().length() > 0 ){
            return Arrays.asList(this.userRole.getRole().split(","));
        }

        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "userCode=" + userCode +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", userRole=" + userRole +
                '}';
    }

}
