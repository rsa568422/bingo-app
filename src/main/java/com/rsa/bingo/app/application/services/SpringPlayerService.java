package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.models.Player;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.repositories.ColorsRepository;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.repositories.PlayerRepository;
import com.rsa.bingo.domain.services.PlayerService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class SpringPlayerService implements PlayerService {

    private final PlayerRepository playerRepository;

    private final CardRepository cardRepository;

    private final ColorsRepository colorsRepository;

    private final CustomizationRepository customizationRepository;

    public SpringPlayerService(PlayerRepository playerRepository, CardRepository cardRepository,
                               ColorsRepository colorsRepository, CustomizationRepository customizationRepository) {
        this.playerRepository = playerRepository;
        this.cardRepository = cardRepository;
        this.colorsRepository = colorsRepository;
        this.customizationRepository = customizationRepository;
    }

    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findById(Integer id) {
        return playerRepository.findById(id);
    }

    @Override
    public Iterable<Player> findByName(String name) {
        return playerRepository.findByName(name);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        cardRepository.findByPlayerId(id).forEach(card -> {
            colorsRepository.findByCardId(card.getId())
                    .forEach(colors -> customizationRepository.delete(card.getId(), colors.getId()));
            cardRepository.delete(card.getId());
        });
        playerRepository.delete(id);
    }
}
