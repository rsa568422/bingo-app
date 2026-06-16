package com.rsa.bingo.app.infrastructure.adapters;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.infrastructure.mappers.PlayerMapper;
import com.rsa.bingo.app.infrastructure.repositories.PlayerCrudRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerRepositoryAdapterTest {

    @Mock
    private PlayerCrudRepository repository;

    @Mock
    private PlayerMapper mapper;

    @InjectMocks
    private PlayerRepositoryAdapter adapter;

    @Test
    void findAll_delegatesToRepositoryAndMapper() {
        var entities = List.of(TestData.playerEntity());
        var players = List.of(TestData.player());
        when(repository.findAll()).thenReturn(entities);
        when(mapper.toPlayers(entities)).thenReturn(players);

        var result = adapter.findAll();

        assertThat(result).isEqualTo(players);
        verify(repository).findAll();
        verify(mapper).toPlayers(entities);
    }

    @Test
    void findById_whenFound_returnsMappedPlayer() {
        var entity = TestData.playerEntity();
        var player = TestData.player();
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toPlayer(entity)).thenReturn(player);

        var result = adapter.findById(1);

        assertThat(result).isPresent().contains(player);
    }

    @Test
    void findById_whenNotFound_returnsEmpty() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        var result = adapter.findById(999);

        assertThat(result).isEmpty();
    }

    @Test
    void findByName_delegatesToRepositoryAndMapper() {
        var entities = List.of(TestData.playerEntity());
        var players = List.of(TestData.player());
        when(repository.findByName("TestPlayer")).thenReturn(entities);
        when(mapper.toPlayers(entities)).thenReturn(players);

        var result = adapter.findByName("TestPlayer");

        assertThat(result).isEqualTo(players);
    }

    @Test
    void save_delegatesToRepositoryAndMapper() {
        var player = TestData.player();
        var entity = TestData.playerEntity();
        when(mapper.toPlayerEntity(player)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toPlayer(entity)).thenReturn(player);

        var result = adapter.save(player);

        assertThat(result).isEqualTo(player);
        verify(repository).save(entity);
    }

    @Test
    void delete_delegatesToRepository() {
        adapter.delete(1);
        verify(repository).deleteById(1);
    }
}
