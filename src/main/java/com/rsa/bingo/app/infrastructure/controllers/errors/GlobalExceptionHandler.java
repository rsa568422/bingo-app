package com.rsa.bingo.app.infrastructure.controllers.errors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MESSAGE = "message";

    @ExceptionHandler(Exception.class)
    public String handleAllUncaughtException(Model model, Exception exception) {
        if (Objects.nonNull(exception.getCause()) && StringUtils.isNoneBlank(exception.getCause().getMessage()))
            model.addAttribute(MESSAGE, exception.getCause().getMessage());
        else if (StringUtils.isNoneBlank(exception.getMessage()))
            model.addAttribute(MESSAGE, exception.getMessage());
        else
            model.addAttribute(MESSAGE, "Error desconocido");
        return "redirect:/commons/error";
    }
}
