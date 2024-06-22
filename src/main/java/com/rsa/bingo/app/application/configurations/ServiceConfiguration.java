package com.rsa.bingo.app.application.configurations;

import com.rsa.bingo.app.application.adapters.CardServiceAdapter;
import com.rsa.bingo.app.application.adapters.CustomizationServiceAdapter;
import com.rsa.bingo.app.application.adapters.PlayerServiceAdapter;
import com.rsa.bingo.app.application.services.DtoCardService;
import com.rsa.bingo.app.application.services.DtoCustomizationService;
import com.rsa.bingo.app.application.services.DtoPlayerService;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.repositories.PlayerRepository;
import com.rsa.bingo.domain.services.CardService;
import com.rsa.bingo.domain.services.CustomizationService;
import com.rsa.bingo.domain.services.PlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public CardService cardService(CardRepository cardRepository, CustomizationRepository customizationRepository) {
        return new CardService(cardRepository, customizationRepository);
    }

    @Bean
    public CustomizationService customizationService(CustomizationRepository customizationRepository) {
        return new CustomizationService(customizationRepository);
    }

    @Bean
    public PlayerService playerService(PlayerRepository playerRepository,
                                       CardRepository cardRepository,
                                       CustomizationRepository customizationRepository) {
        return new PlayerService(playerRepository, cardRepository, customizationRepository);
    }

    @Bean
    public DtoCardService dtoCardService(CardService cardService) {
        return new CardServiceAdapter(cardService);
    }

    @Bean
    public DtoCustomizationService dtoCustomizationService(CustomizationService customizationService) {
        return new CustomizationServiceAdapter(customizationService);
    }

    @Bean
    public DtoPlayerService dtoPlayerService(PlayerService playerService) {
        return new PlayerServiceAdapter(playerService);
    }
}
