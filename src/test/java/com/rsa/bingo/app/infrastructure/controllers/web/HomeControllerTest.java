package com.rsa.bingo.app.infrastructure.controllers.web;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HomeControllerTest {

    private final HomeController controller = new HomeController();

    @Test
    void home_redirectsToPlayer() {
        String result = controller.home();
        assertThat(result).isEqualTo("redirect:/player");
    }
}
