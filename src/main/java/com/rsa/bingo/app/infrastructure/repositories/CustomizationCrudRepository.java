package com.rsa.bingo.app.infrastructure.repositories;

import com.rsa.bingo.app.infrastructure.entities.CustomizationEntity;
import com.rsa.bingo.app.infrastructure.entities.keys.CustomizationKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomizationCrudRepository extends CrudRepository<CustomizationEntity, CustomizationKey> {

    @Query("select c from CustomizationEntity c where c.key.card.id = ?1")
    Iterable<CustomizationEntity> findByCardId(Integer cardId);

    @Modifying
    @Query("delete from CustomizationEntity c where c.key.card.id = ?1")
    void delete(Integer cardId);

    @Modifying
    @Query("delete from CustomizationEntity c where (c.key.card.id = ?1) and (c.key.primaryColor = ?2) and (c.key.secondaryColor = ?3)")
    void delete(Integer cardId, String primaryColor, String secondaryColor);
}
