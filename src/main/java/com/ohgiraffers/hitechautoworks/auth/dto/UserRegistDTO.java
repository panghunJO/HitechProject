package com.ohgiraffers.hitechautoworks.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegistDTO {
    private String userid;
//    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요")
    private String pass;
    private String cpass;
    private String username;
    private String email;
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요")
    private String phone;

    public UserRegistDTO() {
    }

    public UserRegistDTO(String userid, String pass, String cpass, String username, String email, String phone) {
        this.userid = userid;
        this.pass = pass;
        this.cpass = cpass;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserRegistDTO{" +
                "userid='" + userid + '\'' +
                ", pass='" + pass + '\'' +
                ", cpass='" + cpass + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
