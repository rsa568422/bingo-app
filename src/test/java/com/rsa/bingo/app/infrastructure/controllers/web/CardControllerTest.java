package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.application.services.WebCardService;
import com.rsa.bingo.app.application.services.WebCustomizationService;
import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.domain.models.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardControllerTest {

    @Mock
    private WebCardService cardService;

    @Mock
    private WebCustomizationService customizationService;

    @InjectMocks
    private CardController controller;

    private Model model;

    @BeforeEach
    void setUp() {
        model = new ConcurrentModel();
    }

    @Test
    void get_addsCardAndCustomizationsToModel() {
        when(cardService.findById(1)).thenReturn(TestData.cardDTO());
        when(customizationService.findByCardId(1)).thenReturn(List.of(TestData.customizationDTO()));

        var view = controller.get(1, model);

        assertThat(view).isEqualTo("cards/view");
        assertThat(model.getAttribute("card")).isNotNull();
        assertThat(model.getAttribute("customizations")).isNotNull();
        assertThat(model.getAttribute("customization")).isNotNull();
    }

    @Test
    void apply_setsSpecificCustomization() {
        when(cardService.findById(1)).thenReturn(TestData.cardDTO());
        when(customizationService.findByCardId(1)).thenReturn(List.of(TestData.customizationDTO()));

        var view = controller.apply(1, "RED", "BLUE", model);

        assertThat(view).isEqualTo("cards/view");
        var customization = (CustomizationDTO) model.getAttribute("customization");
        assertThat(customization).isNotNull();
        assertThat(customization.getPrimaryName()).isEqualTo(Color.RED.getName());
        assertThat(customization.getSecondaryName()).isEqualTo(Color.BLUE.getName());
    }

    @Test
    void customize_addsCardAndColorsToModel() {
        when(cardService.findById(1)).thenReturn(TestData.cardDTO());

        var view = controller.customize(1, model);

        assertThat(view).isEqualTo("cards/customizer");
        assertThat(model.getAttribute("card")).isNotNull();
        assertThat(model.getAttribute("customization")).isNotNull();
        assertThat(model.getAttribute("colors")).isNotNull();
    }

    @Test
    void customizeWithColors_setsSpecificCustomization() {
        when(cardService.findById(1)).thenReturn(TestData.cardDTO());

        var view = controller.customize(1, "RED", "BLUE", model);

        assertThat(view).isEqualTo("cards/customizer");
        var customization = (CustomizationDTO) model.getAttribute("customization");
        assertThat(customization).isNotNull();
        assertThat(customization.getPrimaryName()).isEqualTo(Color.RED.getName());
    }

    @Test
    void saveCustomization_redirectsToCardView() {
        var dto = TestData.customizationDTO();

        var view = controller.save(dto);

        assertThat(view).isEqualTo("redirect:/card/1");
        verify(customizationService).save(dto);
    }

    @Test
    void builder_addsEmptyCardToModel() {
        var view = controller.builder(1, model);

        assertThat(view).isEqualTo("cards/builder");
        assertThat(model.getAttribute("card")).isNotNull();
        assertThat(model.getAttribute("customization")).isNotNull();
    }

    @Test
    void fill_onSuccess_redirectsToPlayer() {
        var savedCard = TestData.cardDTO();
        when(cardService.save(any(CardDTO.class))).thenReturn(savedCard);

        var card = TestData.cardDTO();
        var view = controller.fill(card, model);

        assertThat(view).isEqualTo("redirect:/player/1");
    }

    @Test
    void fill_onVerifyError_returnsBuilderView() {
        when(cardService.save(any(CardDTO.class))).thenThrow(new VerifyError("Invalid card"));

        var card = new CardDTO(null, new Integer[3][9], 1, "TestPlayer");
        var view = controller.fill(card, model);

        assertThat(view).isEqualTo("cards/builder");
        assertThat(model.getAttribute("message")).isEqualTo("Invalid card");
    }

    @Test
    void deleteCard_redirectsToPlayer() {
        var card = TestData.cardDTO();
        when(cardService.findById(1)).thenReturn(card);

        var view = controller.delete(1);

        assertThat(view).isEqualTo("redirect:/player/1");
        verify(cardService).delete(1);
    }
}
