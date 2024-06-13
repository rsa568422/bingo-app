package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.entities.ColorsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ColorsCrudRepository extends CrudRepository<ColorsEntity, Integer> {

    Iterable<ColorsEntity> findByCardId(Integer cardId);

    @Query("select c from ColorsEntity c join fetch c.card ca join fetch ca.player p where p.id = ?1")
    Iterable<ColorsEntity> findByPlayerId(Integer playerId);
}
