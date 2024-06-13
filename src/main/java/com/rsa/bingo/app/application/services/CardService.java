package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.repositories.CardRepository;

public class CardService {

    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }
}
