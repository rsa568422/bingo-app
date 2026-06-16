package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.application.services.WebCardService;
import com.rsa.bingo.app.application.services.WebPlayerService;
import com.rsa.bingo.app.infrastructure.dtos.PlayerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    private WebPlayerService playerService;

    @Mock
    private WebCardService cardService;

    @InjectMocks
    private PlayerController controller;

    private Model model;

    @BeforeEach
    void setUp() {
        model = new ConcurrentModel();
    }

    @Test
    void list_addsPlayersToModel() {
        var players = List.of(TestData.playerDTO());
        when(playerService.findAll()).thenReturn(players);

        var view = controller.list(model);

        assertThat(view).isEqualTo("players/list");
        assertThat(model.getAttribute("players")).isEqualTo(players);
    }

    @Test
    void get_addsPlayerAndCardsToModel() {
        when(playerService.findById(1)).thenReturn(TestData.playerDTO());
        when(cardService.findByPlayerId(1)).thenReturn(List.of(TestData.cardDTO()));

        var view = controller.get(1, model);

        assertThat(view).isEqualTo("players/view");
        assertThat(model.getAttribute("player")).isNotNull();
        assertThat(model.getAttribute("cards")).isNotNull();
        assertThat(model.getAttribute("customization")).isNotNull();
    }

    @Test
    void create_addsEmptyPlayerToModel() {
        var view = controller.create(model);

        assertThat(view).isEqualTo("players/create");
        assertThat(model.getAttribute("player")).isNotNull();
    }

    @Test
    void delete_redirectsToPlayerList() {
        var view = controller.delete(1);

        assertThat(view).isEqualTo("redirect:/player");
        verify(playerService).delete(1);
    }

    @Test
    void save_withValidPlayer_redirectsToPlayerView() {
        var savedPlayer = TestData.playerDTO();
        when(playerService.save(any(PlayerDTO.class))).thenReturn(savedPlayer);

        var player = new PlayerDTO(null, "NewPlayer");
        var bindingResult = new BeanPropertyBindingResult(player, "player");

        var view = controller.save(player, bindingResult, model);

        assertThat(view).isEqualTo("redirect:/player/1");
    }

    @Test
    void save_withValidationErrors_returnsCreateView() {
        var player = new PlayerDTO(null, "");
        var bindingResult = new BeanPropertyBindingResult(player, "player");
        bindingResult.rejectValue("name", "NotBlank", "El nombre no puede estar vacío");

        var view = controller.save(player, bindingResult, model);

        assertThat(view).isEqualTo("players/create");
        assertThat(model.getAttribute("player")).isEqualTo(player);
    }
}
