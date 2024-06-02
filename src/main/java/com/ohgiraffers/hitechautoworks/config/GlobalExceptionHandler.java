package com.ohgiraffers.hitechautoworks.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException(NoResourceFoundException exception) {
        log.error("404 exception= {} ", exception);
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception) {
        log.error("exception= {} ", exception);
        return "error/404";
    }
}
