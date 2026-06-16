package com.rsa.bingo.app.application.adapters;

import com.rsa.bingo.app.TestData;
import com.rsa.bingo.domain.models.Color;
import com.rsa.bingo.domain.models.Customization;
import com.rsa.bingo.domain.services.CustomizationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomizationServiceAdapterTest {

    @Mock
    private CustomizationService service;

    @InjectMocks
    private CustomizationServiceAdapter adapter;

    @Test
    void findByCardId_addsDefaultWhenNotPresent() {
        var customization = new Customization(1, Color.RED, Color.BLUE);
        when(service.findByCardId(1)).thenReturn(List.of(customization));

        var result = adapter.findByCardId(1);

        assertThat(result).hasSize(2);
        var list = new ArrayList<com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO>();
        result.forEach(list::add);
        assertThat(list.get(0).getPrimaryName()).isEqualTo(Color.BLACK.getName());
        assertThat(list.get(0).getSecondaryName()).isEqualTo(Color.GREY_50_PERCENT.getName());
    }

    @Test
    void findByCardId_doesNotDuplicateDefault() {
        var defaultCustomization = new Customization(1, Color.BLACK, Color.GREY_50_PERCENT);
        when(service.findByCardId(1)).thenReturn(List.of(defaultCustomization));

        var result = adapter.findByCardId(1);

        assertThat(result).hasSize(1);
    }

    @Test
    void save_delegatesAndReturnsDTO() {
        var customization = TestData.customization();
        when(service.save(any(Customization.class))).thenReturn(customization);

        var result = adapter.save(TestData.customizationDTO());

        assertThat(result.getCardId()).isEqualTo(1);
        assertThat(result.getPrimaryName()).isEqualTo(Color.BLACK.getName());
    }

    @Test
    void deleteByCardId_delegatesToService() {
        adapter.delete(1);
        verify(service).delete(1);
    }

    @Test
    void deleteByCustomization_delegatesToService() {
        var dto = TestData.customizationDTO();
        adapter.delete(dto);
        verify(service).delete(any(Customization.class));
    }
}
