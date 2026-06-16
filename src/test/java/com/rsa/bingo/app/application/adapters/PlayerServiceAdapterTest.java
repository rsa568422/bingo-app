package com.rsa.bingo.app.application.adapters;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.infrastructure.dtos.PlayerDTO;
import com.rsa.bingo.domain.models.Player;
import com.rsa.bingo.domain.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceAdapterTest {

    @Mock
    private PlayerService service;

    private PlayerServiceAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new PlayerServiceAdapter(service);
    }

    @Test
    void findAll_returnsDTOList() {
        when(service.findAll()).thenReturn(List.of(TestData.player()));

        Iterable<PlayerDTO> result = adapter.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.iterator().next().getId()).isEqualTo(1);
    }

    @Test
    void findById_whenFound_returnsDTO() {
        when(service.findById(1)).thenReturn(Optional.of(TestData.player()));

        PlayerDTO result = adapter.findById(1);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("TestPlayer");
    }

    @Test
    void findById_whenNotFound_throwsException() {
        when(service.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adapter.findById(999))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Player not found.");
    }

    @Test
    void findByName_returnsDTOList() {
        when(service.findByName("TestPlayer")).thenReturn(List.of(TestData.player()));

        Iterable<PlayerDTO> result = adapter.findByName("TestPlayer");

        assertThat(result).hasSize(1);
    }

    @Test
    void save_delegatesAndReturnsDTO() {
        var player = TestData.player();
        when(service.save(any(Player.class))).thenReturn(player);

        PlayerDTO result = adapter.save(TestData.playerDTO());

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("TestPlayer");
    }

    @Test
    void delete_delegatesToService() {
        adapter.delete(1);
        verify(service).delete(1);
    }
}
