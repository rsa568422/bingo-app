package com.rsa.bingo.app.infrastructure.adapters;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.infrastructure.mappers.CardMapper;
import com.rsa.bingo.app.infrastructure.repositories.CardCrudRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardRepositoryAdapterTest {

    @Mock
    private CardCrudRepository repository;

    @Mock
    private CardMapper mapper;

    @InjectMocks
    private CardRepositoryAdapter adapter;

    @Test
    void findById_whenFound_returnsMappedCard() {
        var entity = TestData.cardEntity();
        var card = TestData.card();
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toCard(entity)).thenReturn(card);

        var result = adapter.findById(1);

        assertThat(result).isPresent().contains(card);
    }

    @Test
    void findById_whenNotFound_returnsEmpty() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        var result = adapter.findById(999);

        assertThat(result).isEmpty();
    }

    @Test
    void findByPlayerId_delegatesToRepositoryAndMapper() {
        var entities = List.of(TestData.cardEntity());
        var cards = List.of(TestData.card());
        when(repository.findByPlayerId(1)).thenReturn(entities);
        when(mapper.toCards(entities)).thenReturn(cards);

        var result = adapter.findByPlayerId(1);

        assertThat(result).isEqualTo(cards);
    }

    @Test
    void save_delegatesToRepositoryAndMapper() {
        var card = TestData.card();
        var entity = TestData.cardEntity();
        when(mapper.toCardEntity(card)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toCard(entity)).thenReturn(card);

        var result = adapter.save(card);

        assertThat(result).isEqualTo(card);
        verify(repository).save(entity);
    }

    @Test
    void delete_delegatesToRepository() {
        adapter.delete(1);
        verify(repository).deleteById(1);
    }
}
