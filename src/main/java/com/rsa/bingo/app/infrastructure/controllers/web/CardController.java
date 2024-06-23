package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.app.application.services.WebCardService;
import com.rsa.bingo.app.application.services.WebCustomizationService;
import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.domain.models.Color;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.rsa.bingo.app.infrastructure.Constants.*;

@Controller
@RequestMapping("/card")
public class CardController {

    private final WebCardService cardService;

    private final WebCustomizationService customizationService;

    public CardController(WebCardService cardService, WebCustomizationService customizationService) {
        this.cardService = cardService;
        this.customizationService = customizationService;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model) {
        model.addAttribute(CARD, cardService.findById(id));
        model.addAttribute(CUSTOMIZATIONS, customizationService.findByCardId(id));
        model.addAttribute(CUSTOMIZATION, defaultCustomization(id));
        return "cards/view";
    }

    @GetMapping("/{id}/{primary}/{secondary}")
    public String applyCustomization(@PathVariable Integer id, @PathVariable String primary,
                            @PathVariable String secondary, Model model) {
        model.addAttribute(CARD, cardService.findById(id));
        model.addAttribute(CUSTOMIZATIONS, customizationService.findByCardId(id));
        model.addAttribute(CUSTOMIZATION, new CustomizationDTO(id, Color.valueOf(primary), Color.valueOf(secondary)));
        return "cards/view";
    }

    @GetMapping("/customize/{id}")
    public String customize(@PathVariable Integer id, Model model) {
        model.addAttribute(CARD, cardService.findById(id));
        model.addAttribute(CUSTOMIZATION, defaultCustomization(id));
        model.addAttribute(COLORS, Color.values());
        return "cards/customizer";
    }

    @GetMapping("/customize/{id}/{primary}/{secondary}")
    public String customize(@PathVariable Integer id, @PathVariable String primary,
                            @PathVariable String secondary, Model model) {
        model.addAttribute(CARD, cardService.findById(id));
        model.addAttribute(CUSTOMIZATION, new CustomizationDTO(id, Color.valueOf(primary), Color.valueOf(secondary)));
        model.addAttribute(COLORS, Color.values());
        return "cards/customizer";
    }


    @GetMapping("/builder")
    public String builder(Model model) {
        model.addAttribute(CARD, new CardDTO());
        model.addAttribute(CUSTOMIZATION, defaultCustomization(null));
        return "cards/builder";
    }

    @PostMapping("/fill")
    public String fill(CardDTO card, Model model) {
        model.addAttribute(CARD, card);
        model.addAttribute(CUSTOMIZATION, defaultCustomization(null));
        model.addAttribute(COLORS, Color.values());
        try {
            card.toCard();
        } catch (VerifyError error) {
            model.addAttribute("message", error.getMessage());
            return "cards/builder";
        }
        return "cards/customizer";
    }
}
