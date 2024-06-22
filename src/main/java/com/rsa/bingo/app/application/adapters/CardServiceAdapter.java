package com.rsa.bingo.app.application.adapters;

import com.rsa.bingo.app.application.services.WebCardService;
import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.domain.services.CardService;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

public class CardServiceAdapter implements WebCardService {

    private final CardService service;

    public CardServiceAdapter(CardService service) {
        this.service = service;
    }

    @Override
    public CardDTO findById(Integer id) {
        return service.findById(id).map(CardDTO::valueOf)
                .orElseThrow(() -> new NoSuchElementException("Card not found."));
    }

    @Override
    public Iterable<CardDTO> findByPlayerId(Integer playerId) {
        return IterableUtils.toList(service.findByPlayerId(playerId)).stream().map(CardDTO::valueOf).toList();
    }

    @Override
    public CardDTO save(CardDTO card) {
        return CardDTO.valueOf(service.save(card.toCard()));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        service.delete(id);
    }

    @Override
    public byte[] toBytes(CardDTO card, CustomizationDTO customization) {
        return service.toBytes(card.toCard(), customization.toCustomization());
    }
}