package com.ohgiraffers.hitechautoworks.common;

public enum UserRole {

    ADMIN("관리자"),
    EMPLOYEE("직원"),
    CUSTOMER("손님"),
    CERTIFIED("인증");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
