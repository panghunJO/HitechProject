package com.ohgiraffers.hitechautoworks.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoResourceFoundException(NoResourceFoundException exception, Model model) {
        log.error("404 exception= {} ", exception);
        model.addAttribute("errorMessage", "404 ERR Resource not found");
        return "error/404";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoHandlerFoundException(NoHandlerFoundException exception, Model model) {
        log.error("404 exception= {} ", exception);
        model.addAttribute("errorMessage", "Handler not found");
        return "error/404";
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException(AccessDeniedException exception, Model model) {
        log.error("403 exception= {} ", exception);
        model.addAttribute("errorMessage", "Access denied");
        return "error/404";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception, Model model) {
        log.error("405 exception= {} ", exception);
        model.addAttribute("errorMessage", "Method not allowed");
        return "error/404";
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception, Model model) {
        log.error("500 exception= {} ", exception);
        model.addAttribute("errorMessage", "Internal server error");
        return "error/404";
    }
}