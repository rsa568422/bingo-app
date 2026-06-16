package com.rsa.bingo.app.application.adapters;

import com.rsa.bingo.app.application.services.WebPlayerService;
import com.rsa.bingo.app.infrastructure.dtos.PlayerDTO;
import com.rsa.bingo.app.infrastructure.utils.IterableMapper;
import com.rsa.bingo.domain.services.PlayerService;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

public class PlayerServiceAdapter implements WebPlayerService {

    private final PlayerService service;

    public PlayerServiceAdapter(PlayerService service) {
        this.service = service;
    }

    @Override
    public Iterable<PlayerDTO> findAll() {
        return IterableMapper.mapToList(service.findAll(), PlayerDTO::valueOf);
    }

    @Override
    public PlayerDTO findById(Integer id) {
        return service.findById(id).map(PlayerDTO::valueOf)
                .orElseThrow(() -> new NoSuchElementException("Player not found."));
    }

    @Override
    public Iterable<PlayerDTO> findByName(String name) {
        return IterableMapper.mapToList(service.findByName(name), PlayerDTO::valueOf);
    }

    @Override
    public PlayerDTO save(PlayerDTO player) {
        return PlayerDTO.valueOf(service.save(player.toPlayer()));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        service.delete(id);
    }
}
