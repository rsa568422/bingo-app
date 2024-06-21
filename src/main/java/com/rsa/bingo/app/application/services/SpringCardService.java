package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.services.CardService;
import org.springframework.transaction.annotation.Transactional;

public class SpringCardService extends CardService {

    public SpringCardService(CardRepository cardRepository, CustomizationRepository customizationRepository) {
        super(cardRepository, customizationRepository);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        super.delete(id);
    }
}
