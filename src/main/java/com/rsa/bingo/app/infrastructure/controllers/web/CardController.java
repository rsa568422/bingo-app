package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.domain.services.CardService;
import com.rsa.bingo.domain.services.CustomizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.rsa.bingo.app.infrastructure.Constants.CARD;
import static com.rsa.bingo.app.infrastructure.Constants.CUSTOMIZATION;
import static com.rsa.bingo.app.infrastructure.Constants.CUSTOMIZATIONS;
import static com.rsa.bingo.app.infrastructure.Constants.defaultCustomization;

@Controller
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    private final CustomizationService customizationService;

    public CardController(CardService cardService, CustomizationService customizationService) {
        this.cardService = cardService;
        this.customizationService = customizationService;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model) {
        model.addAttribute(CARD, cardService.findById(id).orElseThrow(() -> new VerifyError("Card not found")));
        model.addAttribute(CUSTOMIZATIONS, customizationService.findByCardId(id));
        model.addAttribute(CUSTOMIZATION, defaultCustomization(id));
        return "cards/view";
    }

    @GetMapping("/builder")
    public String builder(Model model) {
        model.addAttribute(CARD, new CardDTO());
        model.addAttribute(CUSTOMIZATION, defaultCustomization(null));
        return "cards/builder";
    }
}
