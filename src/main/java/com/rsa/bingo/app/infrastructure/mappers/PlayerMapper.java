package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.PlayerEntity;
import com.rsa.bingo.domain.models.Player;

public interface PlayerMapper {

    Player toPlayer(PlayerEntity playerEntity);

    Iterable<Player> toPlayers(Iterable<PlayerEntity> playerEntities);

    PlayerEntity toPlayerEntity(Player player);
}
