package com.rsa.bingo.app.infrastructure.controllers.rest;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.application.services.WebCardService;
import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardRestControllerTest {

    @Mock
    private WebCardService cardService;

    private CardRestController controller;

    @BeforeEach
    void setUp() {
        controller = new CardRestController(cardService);
    }

    @Test
    void build_delegatesToServiceWithCorrectParams() {
        byte[] expected = new byte[]{1, 2, 3};
        when(cardService.toBytes(any(CardDTO.class), any(CustomizationDTO.class))).thenReturn(expected);

        byte[] result = controller.build(TestData.valuesJson(), "BLACK", "GREY_50_PERCENT");

        assertThat(result).isEqualTo(expected);

        var cardCaptor = ArgumentCaptor.forClass(CardDTO.class);
        var customizationCaptor = ArgumentCaptor.forClass(CustomizationDTO.class);
        verify(cardService).toBytes(cardCaptor.capture(), customizationCaptor.capture());

        assertThat(cardCaptor.getValue().getId()).isNull();
        assertThat(customizationCaptor.getValue().getPrimaryName()).isEqualTo("BLACK");
        assertThat(customizationCaptor.getValue().getSecondaryName()).isEqualTo("GREY_50_PERCENT");
    }
}
