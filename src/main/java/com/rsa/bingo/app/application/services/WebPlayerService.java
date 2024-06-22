package com.rsa.bingo.app.application.services;

import com.rsa.bingo.app.infrastructure.dtos.PlayerDTO;

public interface WebPlayerService {

    Iterable<PlayerDTO> findAll();

    PlayerDTO findById(Integer id);

    Iterable<PlayerDTO> findByName(String name);

    PlayerDTO save(PlayerDTO player);

    void delete(Integer id);
}
