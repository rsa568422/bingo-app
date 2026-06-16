package com.rsa.bingo.app.infrastructure.controllers.errors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestControllerAdvice(basePackages = "com.rsa.bingo.app.infrastructure.controllers.rest")
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    private static final String ERROR = "error";

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NoSuchElementException exception) {
        log.warn("Resource not found: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(ERROR, exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(IllegalArgumentException exception) {
        log.warn("Invalid argument: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(ERROR, exception.getMessage()));
    }

    @ExceptionHandler(VerifyError.class)
    public ResponseEntity<Map<String, String>> handleVerifyError(VerifyError error) {
        log.warn("Validation error: {}", error.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(ERROR, error.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllUncaughtException(Exception exception) {
        log.error("Unexpected error", exception);
        String message;
        if (Objects.nonNull(exception.getCause()) && StringUtils.isNoneBlank(exception.getCause().getMessage()))
            message = exception.getCause().getMessage();
        else if (StringUtils.isNoneBlank(exception.getMessage()))
            message = exception.getMessage();
        else
            message = "Error inesperado";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(ERROR, message));
    }
}
