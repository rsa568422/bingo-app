package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.repositories.PlayerRepository;
import com.rsa.bingo.domain.services.PlayerService;
import org.springframework.transaction.annotation.Transactional;

public class SpringPlayerService extends PlayerService {

    public SpringPlayerService(PlayerRepository playerRepository,
                                  CardRepository cardRepository,
                                  CustomizationRepository customizationRepository) {
        super(playerRepository, cardRepository, customizationRepository);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        super.delete(id);
    }
}
