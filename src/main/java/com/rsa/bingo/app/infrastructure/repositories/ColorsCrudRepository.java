package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.entities.ColorsEntity;
import org.springframework.data.repository.CrudRepository;

public interface ColorsCrudRepository extends CrudRepository<ColorsEntity, Integer> {
}
