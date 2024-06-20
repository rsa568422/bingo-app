package com.rsa.bingo.app.infrastructure.mappers;

import com.rsa.bingo.app.infrastructure.entities.ColorsEntity;
import com.rsa.bingo.domain.models.Colors;

public interface ColorsMapper {

    Colors toColors(ColorsEntity colorsEntity);

    Iterable<Colors> toColors(Iterable<ColorsEntity> colorsEntities);
}
