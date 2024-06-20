package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.entities.CustomizationEntity;
import com.rsa.bingo.app.infrastructure.entities.keys.CustomizationKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomizationCrudRepository extends CrudRepository<CustomizationEntity, CustomizationKey> {

    @Modifying
    @Query("delete from CustomizationEntity cu join fetch key.card ca where ca.id = ?1")
    void deleteByCardId(Integer cardId);

    @Modifying
    @Query("delete from CustomizationEntity cu join fetch key.card ca join fetch key.colors co where ca.id = ?1 and co.id = ?2")
    void deleteByCardId(Integer cardId, Integer colorsId);
}
