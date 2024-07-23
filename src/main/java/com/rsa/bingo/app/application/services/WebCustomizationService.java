package com.rsa.bingo.app.application.services;

import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;

public interface WebCustomizationService {

    Iterable<CustomizationDTO> findByCardId(Integer cardId);

    CustomizationDTO save(CustomizationDTO customization);

    void delete(Integer cardId);

    void delete(CustomizationDTO customization);
}
