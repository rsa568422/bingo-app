package com.rsa.bingo.app.infrastructure.controllers.errors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

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
    void handleAllUncaughtException_withMessage_setsMessage() {
        var exception = new RuntimeException("Something went wrong");

        String view = handler.handleAllUncaughtException(model, exception);

        assertThat(view).isEqualTo("commons/error");
        assertThat(model.getAttribute("message")).isEqualTo("Something went wrong");
    }

    @Test
    void handleAllUncaughtException_withCauseMessage_setsCauseMessage() {
        var cause = new IllegalArgumentException("Root cause");
        var exception = new RuntimeException("Wrapper", cause);

        String view = handler.handleAllUncaughtException(model, exception);

        assertThat(view).isEqualTo("commons/error");
        assertThat(model.getAttribute("message")).isEqualTo("Root cause");
    }

    @Test
    void handleAllUncaughtException_withNullMessage_setsDefault() {
        var exception = new RuntimeException((String) null);

        String view = handler.handleAllUncaughtException(model, exception);

        assertThat(view).isEqualTo("commons/error");
        assertThat(model.getAttribute("message")).isEqualTo("Error desconocido");
    }

    @Test
    void handleAllUncaughtException_withBlankMessage_setsDefault() {
        var exception = new RuntimeException("   ");

        String view = handler.handleAllUncaughtException(model, exception);

        assertThat(view).isEqualTo("commons/error");
        assertThat(model.getAttribute("message")).isEqualTo("Error desconocido");
    }

    @Test
    void handleAllUncaughtException_withCauseButBlankCauseMessage_usesExceptionMessage() {
        var cause = new IllegalArgumentException("   ");
        var exception = new RuntimeException("Fallback message", cause);

        String view = handler.handleAllUncaughtException(model, exception);

        assertThat(view).isEqualTo("commons/error");
        assertThat(model.getAttribute("message")).isEqualTo("Fallback message");
    }
}
