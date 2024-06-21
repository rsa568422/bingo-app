package com.rsa.bingo.app.infrastructure.mappers.defaults;

import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import com.rsa.bingo.app.infrastructure.entities.CustomizationEntity;
import com.rsa.bingo.app.infrastructure.entities.keys.CustomizationKey;
import com.rsa.bingo.app.infrastructure.mappers.CustomizationMapper;
import com.rsa.bingo.domain.models.Color;
import com.rsa.bingo.domain.models.Customization;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultCustomizationMapper implements CustomizationMapper {

    @Override
    public Customization toCustomization(CustomizationEntity customizationEntity) {
        return new Customization(
                customizationEntity.getKey().getCard().getId(),
                Color.valueOf(customizationEntity.getKey().getPrimaryColor()),
                Color.valueOf(customizationEntity.getKey().getSecondaryColor())
        );
    }

    @Override
    public Iterable<Customization> toCustomizations(Iterable<CustomizationEntity> customizationEntities) {
        return IterableUtils.toList(customizationEntities).stream().map(this::toCustomization).toList();
    }

    @Override
    public CustomizationEntity toCustomizationEntity(Customization customization) {
        var card = new CardEntity();
        card.setId(customization.getCardId());
        var customizationKey = new CustomizationKey();
        customizationKey.setCard(card);
        customizationKey.setPrimaryColor(customization.getPrimary().getName());
        customizationKey.setSecondaryColor(customization.getSecondary().getName());
        var customizationEntity = new CustomizationEntity();
        customizationEntity.setKey(customizationKey);
        return customizationEntity;
    }
}
