package com.rsa.bingo.app.infrastructure.configurations;

import com.rsa.bingo.app.application.services.SpringCardService;
import com.rsa.bingo.app.application.services.SpringColorsService;
import com.rsa.bingo.app.application.services.SpringPlayerService;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.ColorsRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.repositories.PlayerRepository;
import com.rsa.bingo.domain.services.CardService;
import com.rsa.bingo.domain.services.ColorsService;
import com.rsa.bingo.domain.services.PlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CardService cardService(CardRepository cardRepository,
                                         ColorsRepository colorsRepository,
                                         CustomizationRepository customizationRepository) {
        return new SpringCardService(cardRepository, colorsRepository, customizationRepository);
    }

    @Bean
    public ColorsService colorsService(ColorsRepository colorsRepository) {
        return new SpringColorsService(colorsRepository);
    }

    @Bean
    public PlayerService playerService(PlayerRepository playerRepository,
                                       CardRepository cardRepository,
                                       ColorsRepository colorsRepository,
                                       CustomizationRepository customizationRepository) {
        return new SpringPlayerService(playerRepository, cardRepository, colorsRepository, customizationRepository);
    }
}
