package com.rsa.bingo.app.infrastructure.controllers.errors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;
    private Model model;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        model = new ConcurrentModel();
    }

    @Test
    void handleNotFound_setsMessage() {
        var exception = new NoSuchElementException("Player not found.");

        String view = handler.handleNotFound(model, exception);

        assertThat(view).isEqualTo("commons/error");
        assertThat(model.getAttribute("message")).isEqualTo("Player not found.");
    }

    @Test
    void handleBadRequest_setsMessage() {
        var exception = new IllegalArgumentException("Invalid input");

        String view = handler.handleBadRequest(model, exception);

        assertThat(view).isEqualTo("commons/error");
        assertThat(model.getAttribute("message")).isEqualTo("Invalid input");
    }

    @Test
    void handleAllUncaughtException_setsGenericMessage() {
        var exception = new RuntimeException("Something went wrong");

        String view = handler.handleAllUncaughtException(model, exception);

        assertThat(view).isEqualTo("commons/error");
        assertThat(model.getAttribute("message")).isEqualTo("Ha ocurrido un error inesperado.");
    }

    @Test
    void handleAllUncaughtException_withNullMessage_setsGenericMessage() {
        var exception = new RuntimeException((String) null);

        String view = handler.handleAllUncaughtException(model, exception);

        assertThat(view).isEqualTo("commons/error");
        assertThat(model.getAttribute("message")).isEqualTo("Ha ocurrido un error inesperado.");
    }
}
