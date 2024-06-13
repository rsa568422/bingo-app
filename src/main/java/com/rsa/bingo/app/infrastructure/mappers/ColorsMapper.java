package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.ColorsEntity;
import com.rsa.bingo.domain.models.Colors;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.stereotype.Component;

@Component
public class ColorsMapper {

    public Colors toColors(ColorsEntity colorsEntity) {
        return new Colors(colorsEntity.getId(), colorsEntity.getPrimaryColor(), colorsEntity.getSecondaryColor());
    }

    public Iterable<Colors> toColors(Iterable<ColorsEntity> colorsEntities) {
        return Streams.of(colorsEntities).map(this::toColors).toList();
    }

    public ColorsEntity toColorsEntity(Colors colors) {
        var colorsEntity = new ColorsEntity();
        colorsEntity.setId(colors.getId());
        colorsEntity.setPrimaryColor(colors.getPrimaryColor());
        colorsEntity.setSecondaryColor(colors.getSecondaryColor());
        return colorsEntity;
    }
}
