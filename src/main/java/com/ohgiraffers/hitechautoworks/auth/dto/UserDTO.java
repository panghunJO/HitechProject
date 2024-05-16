package com.ohgiraffers.hitechautoworks.auth.dto;

import com.ohgiraffers.hitechautoworks.common.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDTO {
    private int userCode;
    private String userId;
    private String password;
    private String userName;
    private UserRole userRole;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String userDepartment;
    private int userCheck;
    private String userAbout;
    private String userImg;

    public UserDTO() {
    }

    public UserDTO(int userCode, String userId, String password, String userName, UserRole userRole, String userEmail, String userPhone, String userAddress, String userDepartment, int userCheck, String userAbout, String userImg) {
        this.userCode = userCode;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userRole = userRole;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userDepartment = userDepartment;
        this.userCheck = userCheck;
        this.userAbout = userAbout;
        this.userImg = userImg;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public int getUserCheck() {
        return userCheck;
    }

    public void setUserCheck(int userCheck) {
        this.userCheck = userCheck;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userCode=" + userCode +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", userRole=" + userRole +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userDepartment='" + userDepartment + '\'' +
                ", userCheck=" + userCheck +
                ", userAbout='" + userAbout + '\'' +
                ", userImg='" + userImg + '\'' +
                '}';
    }
}
