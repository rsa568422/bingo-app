package com.rsa.bingo.app.infrastructure.configurations;

import com.rsa.bingo.app.application.services.SpringCardService;
import com.rsa.bingo.app.application.services.SpringCustomizationService;
import com.rsa.bingo.app.application.services.SpringPlayerService;
import com.rsa.bingo.app.infrastructure.adapters.CardRepositoryAdapter;
import com.rsa.bingo.app.infrastructure.adapters.CustomizationRepositoryAdapter;
import com.rsa.bingo.app.infrastructure.adapters.PlayerRepositoryAdapter;
import com.rsa.bingo.app.infrastructure.mappers.CardMapper;
import com.rsa.bingo.app.infrastructure.mappers.CustomizationMapper;
import com.rsa.bingo.app.infrastructure.mappers.PlayerMapper;
import com.rsa.bingo.app.infrastructure.mappers.defaults.DefaultCardMapper;
import com.rsa.bingo.app.infrastructure.mappers.defaults.DefaultCustomizationMapper;
import com.rsa.bingo.app.infrastructure.mappers.defaults.DefaultPlayerMapper;
import com.rsa.bingo.app.infrastructure.repositories.CardCrudRepository;
import com.rsa.bingo.app.infrastructure.repositories.CustomizationCrudRepository;
import com.rsa.bingo.app.infrastructure.repositories.PlayerCrudRepository;
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
    public PlayerService playerService(PlayerRepository playerRepository,
                                       CardRepository cardRepository,
                                       CustomizationRepository customizationRepository) {
        return new SpringPlayerService(playerRepository, cardRepository, customizationRepository);
    }

    @Bean
    public CardService cardService(CardRepository cardRepository, CustomizationRepository customizationRepository) {
        return new SpringCardService(cardRepository, customizationRepository);
    }

    @Bean
    public CustomizationService customizationService(CustomizationRepository customizationRepository) {
        return new SpringCustomizationService(customizationRepository);
    }

    @Bean
    public CardRepository cardRepository(CardCrudRepository repository, CardMapper mapper) {
        return new CardRepositoryAdapter(repository, mapper);
    }

    @Bean
    public CustomizationRepository customizationRepository(CustomizationCrudRepository repository,
                                                           CustomizationMapper mapper) {
        return new CustomizationRepositoryAdapter(repository, mapper);
    }

    @Bean
    public PlayerRepository playerRepository(PlayerCrudRepository repository, PlayerMapper mapper) {
        return new PlayerRepositoryAdapter(repository, mapper);
    }

    @Bean
    public PlayerMapper playerMapper() {
        return new DefaultPlayerMapper();
    }

    @Bean
    public CardMapper cardMapper(PlayerMapper playerMapper) {
        return new DefaultCardMapper(playerMapper);
    }

    @Bean
    public CustomizationMapper customizationMapper() {
        return new DefaultCustomizationMapper();
    }
}
