package com.rsa.bingo.app.infrastructure.mappers.defaults;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.infrastructure.entities.CustomizationEntity;
import com.rsa.bingo.domain.models.Color;
import com.rsa.bingo.domain.models.Customization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultCustomizationMapperTest {

    private DefaultCustomizationMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new DefaultCustomizationMapper();
    }

    @Test
    void toCustomization_convertsEntityToDomain() {
        var entity = TestData.customizationEntity();
        Customization customization = mapper.toCustomization(entity);
        assertThat(customization.getCardId()).isEqualTo(1);
        assertThat(customization.getPrimary()).isEqualTo(Color.BLACK);
        assertThat(customization.getSecondary()).isEqualTo(Color.GREY_50_PERCENT);
    }

    @Test
    void toCustomizations_convertsMultipleEntities() {
        List<CustomizationEntity> entities = List.of(TestData.customizationEntity());
        Iterable<Customization> customizations = mapper.toCustomizations(entities);
        assertThat(customizations).hasSize(1);
    }

    @Test
    void toCustomizations_emptyList_returnsEmpty() {
        Iterable<Customization> customizations = mapper.toCustomizations(Collections.emptyList());
        assertThat(customizations).isEmpty();
    }

    @Test
    void toCustomizationEntity_convertsDomainToEntity() {
        var customization = TestData.customization();
        CustomizationEntity entity = mapper.toCustomizationEntity(customization);
        assertThat(entity.getKey().getCard().getId()).isEqualTo(customization.getCardId());
        assertThat(entity.getKey().getPrimaryColor()).isEqualTo(Color.BLACK.getName());
        assertThat(entity.getKey().getSecondaryColor()).isEqualTo(Color.GREY_50_PERCENT.getName());
    }

    @Test
    void roundTrip_entityToDomainAndBack() {
        var original = TestData.customizationEntity();
        var customization = mapper.toCustomization(original);
        var result = mapper.toCustomizationEntity(customization);
        assertThat(result.getKey().getCard().getId()).isEqualTo(original.getKey().getCard().getId());
        assertThat(result.getKey().getPrimaryColor()).isEqualTo(original.getKey().getPrimaryColor());
        assertThat(result.getKey().getSecondaryColor()).isEqualTo(original.getKey().getSecondaryColor());
    }
}
