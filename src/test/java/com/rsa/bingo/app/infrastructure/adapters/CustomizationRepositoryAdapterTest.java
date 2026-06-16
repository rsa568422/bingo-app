package com.rsa.bingo.app.infrastructure.adapters;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.app.infrastructure.entities.CustomizationEntity;
import com.rsa.bingo.app.infrastructure.mappers.CustomizationMapper;
import com.rsa.bingo.app.infrastructure.repositories.CustomizationCrudRepository;
import com.rsa.bingo.domain.models.Color;
import com.rsa.bingo.domain.models.Customization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomizationRepositoryAdapterTest {

    @Mock
    private CustomizationCrudRepository repository;

    @Mock
    private CustomizationMapper mapper;

    private CustomizationRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new CustomizationRepositoryAdapter(repository, mapper);
    }

    @Test
    void findByCardId_delegatesToRepositoryAndMapper() {
        var entities = List.of(TestData.customizationEntity());
        var customizations = List.of(TestData.customization());
        when(repository.findByCardId(1)).thenReturn(entities);
        when(mapper.toCustomizations(entities)).thenReturn(customizations);

        Iterable<Customization> result = adapter.findByCardId(1);

        assertThat(result).isEqualTo(customizations);
    }

    @Test
    void save_delegatesToRepositoryAndMapper() {
        var customization = TestData.customization();
        var entity = TestData.customizationEntity();
        when(mapper.toCustomizationEntity(customization)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toCustomization(entity)).thenReturn(customization);

        Customization result = adapter.save(customization);

        assertThat(result).isEqualTo(customization);
        verify(repository).save(entity);
    }

    @Test
    void deleteByCardId_delegatesToRepository() {
        adapter.delete(1);
        verify(repository).delete(1);
    }

    @Test
    void deleteByCustomization_delegatesToRepository() {
        var customization = TestData.customization();
        adapter.delete(customization);
        verify(repository).delete(1, Color.BLACK.getName(), Color.GREY_50_PERCENT.getName());
    }
}
