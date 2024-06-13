package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.entities.CardEntity;
import org.springframework.data.repository.CrudRepository;

public interface CardCrudRepository extends CrudRepository<CardEntity, Integer> {

    Iterable<CardEntity> findByUserId(Integer userId);
}
