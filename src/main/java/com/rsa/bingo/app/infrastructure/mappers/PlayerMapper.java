package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.PlayerEntity;
import com.rsa.bingo.domain.models.Player;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player toPlayer(PlayerEntity playerEntity) {
        return new Player(playerEntity.getId(), playerEntity.getName());
    }

    public Iterable<Player> toPlayers(Iterable<PlayerEntity> playerEntities) {
        return Streams.of(playerEntities).map(this::toPlayer).toList();
    }

    public PlayerEntity toPlayerEntity(Player player) {
        var playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());
        playerEntity.setName(player.getName());
        return playerEntity;
    }
}
