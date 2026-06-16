package com.rsa.bingo.app.application.adapters;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.services.CardService;
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
class CardServiceAdapterTest {

    @Mock
    private CardService service;

    private CardServiceAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new CardServiceAdapter(service);
    }

    @Test
    void findById_whenFound_returnsDTO() {
        when(service.findById(1)).thenReturn(Optional.of(TestData.card()));

        CardDTO result = adapter.findById(1);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getValues()).isDeepEqualTo(TestData.VALUES);
    }

    @Test
    void findById_whenNotFound_throwsException() {
        when(service.findById(999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adapter.findById(999))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Card not found.");
    }

    @Test
    void findByPlayerId_returnsDTOList() {
        when(service.findByPlayerId(1)).thenReturn(List.of(TestData.card()));

        Iterable<CardDTO> result = adapter.findByPlayerId(1);

        assertThat(result).hasSize(1);
    }

    @Test
    void save_delegatesAndReturnsDTO() {
        when(service.save(any(Card.class))).thenReturn(TestData.card());

        CardDTO result = adapter.save(TestData.cardDTO());

        assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void delete_delegatesToService() {
        adapter.delete(1);
        verify(service).delete(1);
    }

    @Test
    void toBytes_delegatesToService() {
        byte[] expected = new byte[]{1, 2, 3};
        when(service.toBytes(any(Card.class), any())).thenReturn(expected);

        byte[] result = adapter.toBytes(TestData.cardDTO(), TestData.customizationDTO());

        assertThat(result).isEqualTo(expected);
    }
}
