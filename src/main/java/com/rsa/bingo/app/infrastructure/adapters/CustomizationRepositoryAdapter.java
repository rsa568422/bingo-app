package com.rsa.bingo.app.infrastructure.adapters;

import com.rsa.bingo.app.infrastructure.mappers.CustomizationMapper;
import com.rsa.bingo.app.infrastructure.repositories.CustomizationCrudRepository;
import com.rsa.bingo.domain.models.Customization;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomizationRepositoryAdapter implements CustomizationRepository {

    private final CustomizationCrudRepository repository;

    private final CustomizationMapper mapper;

    public CustomizationRepositoryAdapter(CustomizationCrudRepository repository, CustomizationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Customization save(Customization customization) {
        return mapper.toCustomization(repository.save(mapper.toCustomizationEntity(customization)));
    }

    @Override
    public void delete(Integer cardId) {
        repository.deleteByCardId(cardId);
    }

    @Override
    public void delete(Integer cardId, Integer colorsId) {
        repository.deleteByCardId(cardId, colorsId);
    }
}
