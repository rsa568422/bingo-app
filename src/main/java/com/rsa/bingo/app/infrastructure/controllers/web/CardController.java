package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.app.application.services.WebCardService;
import com.rsa.bingo.app.application.services.WebCustomizationService;
import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.domain.models.Color;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Path;

import static com.rsa.bingo.app.infrastructure.Constants.CARD;
import static com.rsa.bingo.app.infrastructure.Constants.CUSTOMIZATION;
import static com.rsa.bingo.app.infrastructure.Constants.CUSTOMIZATIONS;
import static com.rsa.bingo.app.infrastructure.Constants.defaultCustomization;

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
    public String customize(@PathVariable Integer id, @PathVariable String primary,
                            @PathVariable String secondary, Model model) {
        model.addAttribute(CARD, cardService.findById(id));
        model.addAttribute(CUSTOMIZATIONS, customizationService.findByCardId(id));
        model.addAttribute(CUSTOMIZATION, new CustomizationDTO(id, Color.valueOf(primary), Color.valueOf(secondary)));
        return "cards/view";
    }

    @GetMapping("/builder")
    public String builder(Model model) {
        model.addAttribute(CARD, new CardDTO());
        model.addAttribute(CUSTOMIZATION, defaultCustomization(null));
        return "cards/builder";
    }

    @GetMapping("/build/{values}/{primary}/{secondary}")
    public String build(@PathVariable String values, @PathVariable String primary, @PathVariable String secondary)
            throws IOException {
        var card = new CardDTO(null, values, null, null);
        var customization = new CustomizationDTO(null, Color.valueOf(primary), Color.valueOf(secondary));
        var bytes = cardService.toBytes(card, customization);
        FileUtils.writeByteArrayToFile(Path.of("./temp.xls").toFile(), bytes);
        return "commons/home";
    }
}
