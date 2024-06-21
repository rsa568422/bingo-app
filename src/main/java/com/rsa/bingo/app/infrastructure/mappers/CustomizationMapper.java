package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.CustomizationEntity;
import com.rsa.bingo.domain.models.Customization;

public interface CustomizationMapper {

    Customization toCustomization(CustomizationEntity customizationEntity);

    Iterable<Customization> toCustomizations(Iterable<CustomizationEntity> customizationEntities);

    CustomizationEntity toCustomizationEntity(Customization customization);
}
