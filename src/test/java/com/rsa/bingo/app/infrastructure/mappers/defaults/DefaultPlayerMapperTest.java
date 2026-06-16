package com.rsa.bingo.app.infrastructure.mappers.defaults;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.infrastructure.entities.PlayerEntity;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultPlayerMapperTest {

    private final DefaultPlayerMapper mapper = new DefaultPlayerMapper();

    @Test
    void toPlayer_convertsEntityToDomain() {
        var entity = TestData.playerEntity();
        var player = mapper.toPlayer(entity);
        assertThat(player.getId()).isEqualTo(entity.getId());
        assertThat(player.getName()).isEqualTo(entity.getName());
    }

    @Test
    void toPlayers_convertsMultipleEntities() {
        var entity1 = TestData.playerEntity();
        var entity2 = new PlayerEntity();
        entity2.setId(2);
        entity2.setName("Player2");
        var entities = List.of(entity1, entity2);

        var players = mapper.toPlayers(entities);
        assertThat(players).hasSize(2);
    }

    @Test
    void toPlayers_emptyList_returnsEmpty() {
        var players = mapper.toPlayers(Collections.emptyList());
        assertThat(players).isEmpty();
    }

    @Test
    void toPlayerEntity_convertsDomainToEntity() {
        var player = TestData.player();
        var entity = mapper.toPlayerEntity(player);
        assertThat(entity.getId()).isEqualTo(player.getId());
        assertThat(entity.getName()).isEqualTo(player.getName());
    }

    @Test
    void roundTrip_entityToDomainAndBack() {
        var original = TestData.playerEntity();
        var player = mapper.toPlayer(original);
        var result = mapper.toPlayerEntity(player);
        assertThat(result.getId()).isEqualTo(original.getId());
        assertThat(result.getName()).isEqualTo(original.getName());
    }
}
