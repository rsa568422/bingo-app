package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.models.Player;
import com.rsa.bingo.domain.repositories.PlayerRepository;

import java.util.Optional;

public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Iterable<Player> getAll() {
        return repository.findAll();
    }

    public Optional<Player> get(Integer id) {
        return repository.findById(id);
    }

    public Optional<Player> get(String name) {
        return repository.findByName(name);
    }

    public Player save(Player player) {
        return repository.save(player);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
