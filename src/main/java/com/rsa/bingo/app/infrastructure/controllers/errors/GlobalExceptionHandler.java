package com.rsa.bingo.app.infrastructure.controllers.errors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;
import java.util.Objects;

@ControllerAdvice(basePackages = "com.rsa.bingo.app.infrastructure.controllers.web")
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Model model, NoSuchElementException exception) {
        log.warn("Resource not found: {}", exception.getMessage());
        model.addAttribute(MESSAGE, exception.getMessage());
        return "commons/error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(Model model, IllegalArgumentException exception) {
        log.warn("Invalid argument: {}", exception.getMessage());
        model.addAttribute(MESSAGE, exception.getMessage());
        return "commons/error";
    }

    @ExceptionHandler(VerifyError.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleVerifyError(Model model, VerifyError error) {
        log.warn("Validation error: {}", error.getMessage());
        model.addAttribute(MESSAGE, error.getMessage());
        return "commons/error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAllUncaughtException(Model model, Exception exception) {
        log.error("Unexpected error", exception);
        if (Objects.nonNull(exception.getCause()) && StringUtils.isNoneBlank(exception.getCause().getMessage()))
            model.addAttribute(MESSAGE, exception.getCause().getMessage());
        else if (StringUtils.isNoneBlank(exception.getMessage()))
            model.addAttribute(MESSAGE, exception.getMessage());
        else
            model.addAttribute(MESSAGE, "Error desconocido");
        return "commons/error";
    }
}
