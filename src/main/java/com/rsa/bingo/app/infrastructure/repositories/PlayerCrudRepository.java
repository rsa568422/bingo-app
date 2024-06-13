package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerCrudRepository extends CrudRepository<PlayerEntity, Integer> {

    Optional<PlayerEntity> findByName(String name);
}
