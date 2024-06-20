package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.models.Colors;
import com.rsa.bingo.domain.repositories.ColorsRepository;
import com.rsa.bingo.domain.services.ColorsService;

import java.util.Optional;

public class SpringColorsService implements ColorsService {

    private final ColorsRepository colorsRepository;

    public SpringColorsService(ColorsRepository colorsRepository) {
        this.colorsRepository = colorsRepository;
    }

    @Override
    public Iterable<Colors> findAll() {
        return colorsRepository.findAll();
    }

    @Override
    public Optional<Colors> findById(Integer id) {
        return colorsRepository.findById(id);
    }

    @Override
    public Iterable<Colors> findByCardId(Integer cardId) {
        return colorsRepository.findByCardId(cardId);
    }
}
