package com.rsa.bingo.app.application.configurations;

import com.rsa.bingo.app.application.services.SpringCardService;
import com.rsa.bingo.app.application.services.SpringCustomizationService;
import com.rsa.bingo.app.application.services.SpringPlayerService;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.repositories.PlayerRepository;
import com.rsa.bingo.domain.services.CardService;
import com.rsa.bingo.domain.services.CustomizationService;
import com.rsa.bingo.domain.services.PlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CardService cardService(CardRepository cardRepository, CustomizationRepository customizationRepository) {
        return new SpringCardService(cardRepository, customizationRepository);
    }

    @Bean
    public CustomizationService customizationService(CustomizationRepository customizationRepository) {
        return new SpringCustomizationService(customizationRepository);
    }

    @Bean
    public PlayerService playerService(PlayerRepository playerRepository,
                                       CardRepository cardRepository,
                                       CustomizationRepository customizationRepository) {
        return new SpringPlayerService(playerRepository, cardRepository, customizationRepository);
    }
}
