package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.models.Colors;
import com.rsa.bingo.domain.repositories.ColorsRepository;

import java.util.Optional;

public class ColorsService {

    private final ColorsRepository repository;

    public ColorsService(ColorsRepository repository) {
        this.repository = repository;
    }

    public Optional<Colors> get(Integer id) {
        return repository.findById(id);
    }

    public Iterable<Colors> getByPlayerId(Integer playerId) {
        return repository.findByPlayerId(playerId);
    }

    public Iterable<Colors> getByCardId(Integer cardId) {
        return repository.findByCardId(cardId);
    }

    public Colors save(Colors colors) {
        return repository.save(colors);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }
}
