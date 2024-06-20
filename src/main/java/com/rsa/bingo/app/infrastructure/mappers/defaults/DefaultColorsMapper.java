package com.rsa.bingo.app.infrastructure.mappers.defaults;

import com.rsa.bingo.app.infrastructure.entities.ColorsEntity;
import com.rsa.bingo.app.infrastructure.mappers.ColorsMapper;
import com.rsa.bingo.domain.models.Colors;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultColorsMapper implements ColorsMapper {

    @Override
    public Colors toColors(ColorsEntity colorsEntity) {
        return new Colors(colorsEntity.getId(), colorsEntity.getPrimaryRGB(), colorsEntity.getSecondaryRGB());
    }

    @Override
    public Iterable<Colors> toColors(Iterable<ColorsEntity> colorsEntities) {
        return IterableUtils.toList(colorsEntities).stream().map(this::toColors).toList();
    }
}
