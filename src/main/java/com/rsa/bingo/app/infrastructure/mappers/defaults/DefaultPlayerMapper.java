package com.rsa.bingo.app.infrastructure.mappers.defaults;

import com.rsa.bingo.app.infrastructure.entities.PlayerEntity;
import com.rsa.bingo.app.infrastructure.mappers.PlayerMapper;
import com.rsa.bingo.domain.models.Player;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultPlayerMapper implements PlayerMapper {

    @Override
    public Player toPlayer(PlayerEntity playerEntity) {
        return new Player(playerEntity.getId(), playerEntity.getName());
    }

    @Override
    public Iterable<Player> toPlayers(Iterable<PlayerEntity> playerEntities) {
        return IterableUtils.toList(playerEntities).stream().map(this::toPlayer).toList();
    }

    @Override
    public PlayerEntity toPlayerEntity(Player player) {
        var playerEntity = new PlayerEntity();
        playerEntity.setId(player.getId());
        playerEntity.setName(player.getName());
        return playerEntity;
    }
}
