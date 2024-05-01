package com.ohgiraffers.hitechautoworks.config.handler;

import com.ohgiraffers.hitechautoworks.common.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String role = determineUserRole(authentication);
        switch (role) {
            case "ADMIN":
                System.out.println("로그인 시도......");
                response.sendRedirect("/user/dashboard");
                break;
            case "EMPLOYEE":
                response.sendRedirect("/user/dashboard");
                break;
            case "CUSTOMER":
                response.sendRedirect("/customer/dashboard");
                break;
            case "CERTIFIED":
                response.sendRedirect("/certified");
                break;
            default:
                response.sendRedirect("/access-denied");
                break;
        }
    }

    private String determineUserRole(Authentication authentication) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserRole.ADMIN.getRole()))) {
            return "ADMIN";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserRole.EMPLOYEE.getRole()))) {
            return "EMPLOYEE";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserRole.CUSTOMER.getRole()))) {
            return "CUSTOMER";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserRole.CERTIFIED.getRole()))) {
            return "CERTIFIED";
        } else {
            return "UNKNOWN";
        }
    }
}
