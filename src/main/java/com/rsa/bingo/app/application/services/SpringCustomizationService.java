package com.rsa.bingo.app.application.services;

import com.rsa.bingo.domain.models.Customization;
import com.rsa.bingo.domain.repositories.CustomizationRepository;
import com.rsa.bingo.domain.services.CustomizationService;

import java.util.LinkedList;

import static com.rsa.bingo.app.infrastructure.Constants.defaultCustomization;

public class SpringCustomizationService extends CustomizationService {

    public SpringCustomizationService(CustomizationRepository customizationRepository) {
        super(customizationRepository);
    }

    @Override
    public Iterable<Customization> findByCardId(Integer cardId) {
        var customizations = new LinkedList<Customization>();
        customizations.add(defaultCustomization(cardId));
        super.findByCardId(cardId).forEach(customizations::add);
        return customizations;
    }
}
