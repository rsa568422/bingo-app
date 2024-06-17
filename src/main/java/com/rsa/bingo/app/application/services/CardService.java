package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.models.Colors;
import com.rsa.bingo.domain.repositories.CardRepository;
import com.rsa.bingo.domain.utils.Parser;

import java.io.IOException;
import java.util.Optional;

public class CardService {

    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public Iterable<Card> getAll() {
        return repository.findAll();
    }

    public Optional<Card> get(Integer id) {
        return repository.findById(id);
    }

    public Iterable<Card> getByPlayerId(Integer playerId) {
        return repository.findByPlayerId(playerId);
    }

    public Card save(Card card) {
        return repository.save(card);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

    public byte[] toBytes(Card card, Colors colors) throws IOException {
        return Parser.getBytes(card, colors);
    }
}
