package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.mappers.CardMapper;
import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.repositories.CardRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CardRepositoryImpl implements CardRepository {

    private final CardCrudRepository repository;

    private final CardMapper mapper;

    public CardRepositoryImpl(CardCrudRepository repository, CardMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<Card> findAll() {
        return mapper.toCards(repository.findAll());
    }

    @Override
    public Optional<Card> findById(Integer id) {
        return repository.findById(id).map(mapper::toCard);
    }

    @Override
    public Iterable<Card> findByPlayerId(Integer playerId) {
        return mapper.toCards(repository.findByPlayerId(playerId));
    }

    @Override
    public Card save(Card card) {
        return mapper.toCard(repository.save(mapper.toCardEntity(card)));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
