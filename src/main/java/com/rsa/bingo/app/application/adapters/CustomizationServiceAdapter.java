package com.rsa.bingo.app.application.adapters;

import com.rsa.bingo.app.application.services.DtoCustomizationService;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.domain.services.CustomizationService;

import java.util.LinkedList;

import static com.rsa.bingo.app.infrastructure.Constants.defaultCustomization;

public class CustomizationServiceAdapter implements DtoCustomizationService {

    private final CustomizationService service;

    public CustomizationServiceAdapter(CustomizationService service) {
        this.service = service;
    }

    @Override
    public Iterable<CustomizationDTO> findByCardId(Integer cardId) {
        var customizations = new LinkedList<CustomizationDTO>();
        customizations.add(CustomizationDTO.valueOf(defaultCustomization(cardId)));
        service.findByCardId(cardId)
                .forEach(customization -> customizations.add(CustomizationDTO.valueOf(customization)));
        return customizations;
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
