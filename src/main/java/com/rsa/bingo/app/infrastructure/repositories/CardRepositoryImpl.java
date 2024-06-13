package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.repositories.CardRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CardRepositoryImpl implements CardRepository {

    private final CardCrudRepository repository;

    public CardRepositoryImpl(CardCrudRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Card> findAll() {
        return null;
    }

    @Override
    public Optional<Card> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Iterable<Card> findByUserId(Integer integer) {
        return null;
    }
}
