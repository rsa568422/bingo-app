package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.CustomizationEntity;
import com.rsa.bingo.domain.models.Customization;
import org.springframework.stereotype.Component;

@Component
public class CustomizationMapperImpl implements CustomizationMapper {
    @Override
    public Customization toCustomization(CustomizationEntity customizationEntity) {
        return null;
    }

    @Override
    public CustomizationEntity toCustomizationEntity(Customization customization) {
        return null;
    }
}
