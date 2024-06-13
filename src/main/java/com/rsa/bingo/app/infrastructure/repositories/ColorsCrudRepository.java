package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.entities.ColorsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ColorsCrudRepository extends CrudRepository<ColorsEntity, Integer> {

    Iterable<ColorsEntity> findByCardId(Integer cardId);

    @Query("select c from Colors c join c.card ca join ca.user u where u.id = ?")
    Iterable<ColorsEntity> findByUserId(Integer userId);
}
