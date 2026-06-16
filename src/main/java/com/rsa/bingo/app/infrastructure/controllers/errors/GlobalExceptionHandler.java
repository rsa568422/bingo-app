package com.rsa.bingo.app.infrastructure.controllers.errors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String MESSAGE = "message";

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFound(Model model, NoSuchElementException exception) {
        model.addAttribute(MESSAGE, exception.getMessage());
        return "commons/error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleBadRequest(Model model, IllegalArgumentException exception) {
        model.addAttribute(MESSAGE, exception.getMessage());
        return "commons/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleAllUncaughtException(Model model, Exception exception) {
        log.error("Unexpected error", exception);
        model.addAttribute(MESSAGE, "Ha ocurrido un error inesperado.");
        return "commons/error";
    }
}
