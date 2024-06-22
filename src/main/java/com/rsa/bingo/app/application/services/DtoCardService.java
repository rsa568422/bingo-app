package com.rsa.bingo.app.application.services;

import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;

public interface DtoCardService {

    CardDTO findById(Integer id);

    Iterable<CardDTO> findByPlayerId(Integer playerId);

    CardDTO save(CardDTO card);

    void delete(Integer id);

    byte[] toBytes(CardDTO card, CustomizationDTO customization);
}
