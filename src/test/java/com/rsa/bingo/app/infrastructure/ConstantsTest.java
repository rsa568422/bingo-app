package com.rsa.bingo.app.infrastructure;

import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.domain.models.Color;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConstantsTest {

    @Test
    void stringConstants_haveExpectedValues() {
        assertThat(Constants.PLAYERS).isEqualTo("players");
        assertThat(Constants.PLAYER).isEqualTo("player");
        assertThat(Constants.CARDS).isEqualTo("cards");
        assertThat(Constants.CARD).isEqualTo("card");
        assertThat(Constants.COLORS).isEqualTo("colors");
        assertThat(Constants.CUSTOMIZATION).isEqualTo("customization");
        assertThat(Constants.CUSTOMIZATIONS).isEqualTo("customizations");
    }

    @Test
    void defaultCustomization_returnsBlackAndGrey() {
        CustomizationDTO dto = Constants.defaultCustomization(5);
        assertThat(dto.getCardId()).isEqualTo(5);
        assertThat(dto.getPrimaryName()).isEqualTo(Color.BLACK.getName());
        assertThat(dto.getPrimaryRGB()).isEqualTo(Color.BLACK.getRgb());
        assertThat(dto.getSecondaryName()).isEqualTo(Color.GREY_50_PERCENT.getName());
        assertThat(dto.getSecondaryRGB()).isEqualTo(Color.GREY_50_PERCENT.getRgb());
    }

    @Test
    void defaultCustomization_withNullCardId() {
        CustomizationDTO dto = Constants.defaultCustomization(null);
        assertThat(dto.getCardId()).isNull();
        assertThat(dto.getPrimaryName()).isEqualTo(Color.BLACK.getName());
    }
}
