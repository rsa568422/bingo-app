package com.rsa.bingo.app.infrastructure.adapters;

import com.rsa.bingo.app.infrastructure.mappers.PlayerMapper;
import com.rsa.bingo.app.infrastructure.repositories.PlayerCrudRepository;
import com.rsa.bingo.domain.models.Player;
import com.rsa.bingo.domain.repositories.PlayerRepository;

import java.util.Optional;

public class PlayerRepositoryAdapter implements PlayerRepository {

    private final PlayerCrudRepository repository;

    private final PlayerMapper mapper;

    public PlayerRepositoryAdapter(PlayerCrudRepository repository, PlayerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<Player> findAll() {
        return mapper.toPlayers(repository.findAll());
    }

    @Override
    public Optional<Player> findById(Integer id) {
        return repository.findById(id).map(mapper::toPlayer);
    }

    @Override
    public Iterable<Player> findByName(String name) {
        return mapper.toPlayers(repository.findByName(name));
    }

    @Override
    public Player save(Player player) {
        return mapper.toPlayer(repository.save(mapper.toPlayerEntity(player)));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
