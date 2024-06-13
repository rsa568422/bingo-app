package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.repositories.ColorsRepository;

public class ColorsService {

    private final ColorsRepository repository;

    public ColorsService(ColorsRepository repository) {
        this.repository = repository;
    }
}
