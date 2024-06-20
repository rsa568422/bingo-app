package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.ColorsRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.services.CardService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class SpringCardService implements CardService {

    private final CardRepository cardRepository;

    private final ColorsRepository colorsRepository;

    private final CustomizationRepository customizationRepository;

    public SpringCardService(CardRepository cardRepository, ColorsRepository colorsRepository, CustomizationRepository customizationRepository) {
        this.cardRepository = cardRepository;
        this.colorsRepository = colorsRepository;
        this.customizationRepository = customizationRepository;
    }

    @Override
    public Optional<Card> findById(Integer id) {
        return cardRepository.findById(id);
    }

    @Override
    public Iterable<Card> findByPlayerId(Integer playerId) {
        return cardRepository.findByPlayerId(playerId);
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        colorsRepository.findByCardId(id).forEach(colors -> customizationRepository.delete(id, colors.getId()));
        cardRepository.delete(id);
    }
}
