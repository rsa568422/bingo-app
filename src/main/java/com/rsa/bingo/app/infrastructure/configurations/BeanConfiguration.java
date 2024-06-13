package com.rsa.bingo.app.infrastructure.configurations;

import com.rsa.bingo.app.application.services.CardService;
import com.rsa.bingo.app.application.services.ColorsService;
import com.rsa.bingo.app.application.services.PlayerService;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.ColorsRepository;
import com.rsa.bingo.domain.repositories.PlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CardService cardService(CardRepository repository) {
        return new CardService(repository);
    }

    @Bean
    public ColorsService colorsService(ColorsRepository repository) {
        return new ColorsService(repository);
    }

    @Bean
    public PlayerService playerService(PlayerRepository repository) {
        return new PlayerService(repository);
    }
}
