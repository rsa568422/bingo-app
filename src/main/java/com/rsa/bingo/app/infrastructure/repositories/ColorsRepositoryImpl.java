package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.mappers.ColorsMapper;
import com.rsa.bingo.domain.models.Colors;
import com.rsa.bingo.domain.repositories.ColorsRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ColorsRepositoryImpl implements ColorsRepository {

    private final ColorsCrudRepository repository;

    private final ColorsMapper mapper;

    public ColorsRepositoryImpl(ColorsCrudRepository repository, ColorsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Colors> findById(Integer id) {
        return repository.findById(id).map(mapper::toColors);
    }

    @Override
    public Iterable<Colors> findByCardId(Integer cardId) {
        return mapper.toColors(repository.findByCardId(cardId));
    }

    @Override
    public Iterable<Colors> findByPlayerId(Integer playerId) {
        return mapper.toColors(repository.findByPlayerId(playerId));
    }

    @Override
    public Colors save(Colors colors) {
        return mapper.toColors(repository.save(mapper.toColorsEntity(colors)));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
