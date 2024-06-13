package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerCrudRepository extends CrudRepository<PlayerEntity, Integer> {
}
