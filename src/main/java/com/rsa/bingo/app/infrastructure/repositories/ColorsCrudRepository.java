package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.entities.ColorsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ColorsCrudRepository extends CrudRepository<ColorsEntity, Integer> {

    @Query("select co from ColorsEntity co join fetch CustomizationEntity cu on co = cu.key.colors where cu.key.card.id = ?1")
    Iterable<ColorsEntity> findByCardId(Integer cardId);
}
