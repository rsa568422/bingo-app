package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.PlayerEntity;
import com.rsa.bingo.domain.models.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public Player toPlayer(PlayerEntity playerEntity) {
        return null;
    }

    @Override
    public Iterable<Player> toPlayers(Iterable<PlayerEntity> playerEntities) {
        return null;
    }

    @Override
    public PlayerEntity toPlayerEntity(Player player) {
        return null;
    }
}
