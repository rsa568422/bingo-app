package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.ColorsEntity;
import com.rsa.bingo.domain.models.Colors;
import org.springframework.stereotype.Component;

@Component
public class ColorsMapperImpl implements ColorsMapper {

    @Override
    public Colors toColors(ColorsEntity colorsEntity) {
        return null;
    }

    @Override
    public Iterable<Colors> toColors(Iterable<ColorsEntity> colorsEntities) {
        return null;
    }
}
