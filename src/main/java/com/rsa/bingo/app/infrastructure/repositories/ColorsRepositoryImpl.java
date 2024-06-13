package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.domain.models.Colors;
import com.rsa.bingo.domain.repositories.ColorsRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ColorsRepositoryImpl implements ColorsRepository {

    private final ColorsCrudRepository repository;

    public ColorsRepositoryImpl(ColorsCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Colors> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Iterable<Colors> findByUserId(Integer integer) {
        return null;
    }
}
