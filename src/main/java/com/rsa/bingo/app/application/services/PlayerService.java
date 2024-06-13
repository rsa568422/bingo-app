package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.repositories.PlayerRepository;

public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }
}
