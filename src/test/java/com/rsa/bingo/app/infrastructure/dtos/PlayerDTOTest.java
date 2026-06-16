package com.rsa.bingo.app.infrastructure.dtos;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.domain.models.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerDTOTest {

    @Test
    void toPlayer_convertsCorrectly() {
        var dto = TestData.playerDTO();
        Player player = dto.toPlayer();
        assertThat(player.getId()).isEqualTo(1);
        assertThat(player.getName()).isEqualTo("TestPlayer");
    }

    @Test
    void valueOf_convertsFromPlayer() {
        var player = TestData.player();
        PlayerDTO dto = PlayerDTO.valueOf(player);
        assertThat(dto.getId()).isEqualTo(player.getId());
        assertThat(dto.getName()).isEqualTo(player.getName());
    }

    @Test
    void roundTrip_preservesData() {
        var original = TestData.playerDTO();
        var player = original.toPlayer();
        var result = PlayerDTO.valueOf(player);
        assertThat(result.getId()).isEqualTo(original.getId());
        assertThat(result.getName()).isEqualTo(original.getName());
    }

    @Test
    void noArgConstructor_createsEmptyInstance() {
        var dto = new PlayerDTO();
        assertThat(dto.getId()).isNull();
        assertThat(dto.getName()).isNull();
    }

    @Test
    void setters_updateFields() {
        var dto = new PlayerDTO();
        dto.setId(5);
        dto.setName("Updated");
        assertThat(dto.getId()).isEqualTo(5);
        assertThat(dto.getName()).isEqualTo("Updated");
    }
}
