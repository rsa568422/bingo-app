package com.rsa.bingo.app.infrastructure.dtos;

import com.rsa.bingo.domain.models.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private Integer id;

    private String name;

    public Player toPlayer() {
        return new Player(id, name);
    }

    public static PlayerDTO valueOf(Player player) {
        return new PlayerDTO(player.getId(), player.getName());
    }
}
