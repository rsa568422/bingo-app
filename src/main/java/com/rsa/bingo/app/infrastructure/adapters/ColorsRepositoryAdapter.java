package com.rsa.bingo.app.infrastructure.adapters;

import com.rsa.bingo.app.infrastructure.mappers.ColorsMapper;
import com.rsa.bingo.app.infrastructure.repositories.ColorsCrudRepository;
import com.rsa.bingo.domain.models.Colors;
import com.rsa.bingo.domain.repositories.ColorsRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ColorsRepositoryAdapter implements ColorsRepository {

    private final ColorsCrudRepository repository;

    private final ColorsMapper mapper;

    public ColorsRepositoryAdapter(ColorsCrudRepository repository, ColorsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<Colors> findAll() {
        return mapper.toColors(repository.findAll());
    }

    @Override
    public Optional<Colors> findById(Integer id) {
        return repository.findById(id).map(mapper::toColors);
    }

    @Override
    public Iterable<Colors> findByCardId(Integer cardId) {
        return mapper.toColors(repository.findByCardId(cardId));
    }
}
