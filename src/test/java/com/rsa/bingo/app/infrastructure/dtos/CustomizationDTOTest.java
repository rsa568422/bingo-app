package com.rsa.bingo.app.infrastructure.dtos;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.domain.models.Color;
import com.rsa.bingo.domain.models.Customization;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomizationDTOTest {

    @Test
    void toCustomization_convertsCorrectly() {
        var dto = TestData.customizationDTO();
        Customization customization = dto.toCustomization();
        assertThat(customization.getCardId()).isEqualTo(1);
        assertThat(customization.getPrimary()).isEqualTo(Color.BLACK);
        assertThat(customization.getSecondary()).isEqualTo(Color.GREY_50_PERCENT);
    }

    @Test
    void valueOf_convertsFromCustomization() {
        var customization = TestData.customization();
        CustomizationDTO dto = CustomizationDTO.valueOf(customization);
        assertThat(dto.getCardId()).isEqualTo(customization.getCardId());
        assertThat(dto.getPrimaryName()).isEqualTo(Color.BLACK.getName());
        assertThat(dto.getPrimaryRGB()).isEqualTo(Color.BLACK.getRgb());
        assertThat(dto.getSecondaryName()).isEqualTo(Color.GREY_50_PERCENT.getName());
        assertThat(dto.getSecondaryRGB()).isEqualTo(Color.GREY_50_PERCENT.getRgb());
    }

    @Test
    void colorConstructor_setsFieldsCorrectly() {
        var dto = new CustomizationDTO(5, Color.RED, Color.BLUE);
        assertThat(dto.getCardId()).isEqualTo(5);
        assertThat(dto.getPrimaryName()).isEqualTo(Color.RED.getName());
        assertThat(dto.getPrimaryRGB()).isEqualTo(Color.RED.getRgb());
        assertThat(dto.getSecondaryName()).isEqualTo(Color.BLUE.getName());
        assertThat(dto.getSecondaryRGB()).isEqualTo(Color.BLUE.getRgb());
    }

    @Test
    void roundTrip_preservesData() {
        var original = TestData.customizationDTO();
        var customization = original.toCustomization();
        var result = CustomizationDTO.valueOf(customization);
        assertThat(result).isEqualTo(original);
    }

    @Test
    void noArgConstructor_createsEmptyInstance() {
        var dto = new CustomizationDTO();
        assertThat(dto.getCardId()).isNull();
        assertThat(dto.getPrimaryName()).isNull();
        assertThat(dto.getSecondaryName()).isNull();
    }

    @Test
    void equals_worksCorrectly() {
        var dto1 = TestData.customizationDTO();
        var dto2 = TestData.customizationDTO();
        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    void equals_differentValues_notEqual() {
        var dto1 = new CustomizationDTO(1, Color.BLACK, Color.RED);
        var dto2 = new CustomizationDTO(1, Color.BLACK, Color.BLUE);
        assertThat(dto1).isNotEqualTo(dto2);
    }
}
