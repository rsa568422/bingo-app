package com.rsa.bingo.app.infrastructure.dtos;

import com.rsa.bingo.domain.models.Player;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String name;

    public Player toPlayer() {
        return new Player(id, name);
    }

    public static PlayerDTO valueOf(Player player) {
        return new PlayerDTO(player.getId(), player.getName());
    }
}
