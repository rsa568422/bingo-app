package com.rsa.bingo.app.application.adapters;

import com.rsa.bingo.app.application.services.WebCustomizationService;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.domain.services.CustomizationService;
import org.apache.commons.collections4.IterableUtils;

import static com.rsa.bingo.app.infrastructure.Constants.defaultCustomization;

public class CustomizationServiceAdapter implements WebCustomizationService {

    private final CustomizationService service;

    public CustomizationServiceAdapter(CustomizationService service) {
        this.service = service;
    }

    @Override
    public Iterable<CustomizationDTO> findByCardId(Integer cardId) {
        var defaultCustomization = defaultCustomization(cardId).toCustomization();
        var customizations = IterableUtils.toList(service.findByCardId(cardId));
        if (!customizations.contains(defaultCustomization)) {
            customizations.addFirst(defaultCustomization);
        }
        return customizations.stream().map(CustomizationDTO::valueOf).toList();
    }

    @Override
    public CustomizationDTO save(CustomizationDTO customization) {
        return CustomizationDTO.valueOf(service.save(customization.toCustomization()));
    }

    @Override
    public void delete(Integer cardId) {
        service.delete(cardId);
    }

    @Override
    public void delete(CustomizationDTO customization) {
        service.delete(customization.toCustomization());
    }
}
