package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.domain.models.Player;
import com.rsa.bingo.domain.repositories.PlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    private final PlayerCrudRepository repository;

    public PlayerRepositoryImpl(PlayerCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Player> findAll() {
        return null;
    }

    @Override
    public Optional<Player> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<Player> findByName(String s) {
        return Optional.empty();
    }
}
