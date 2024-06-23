package com.rsa.bingo.app.infrastructure.controllers.rest;

import com.rsa.bingo.app.application.services.WebCardService;
import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.app.infrastructure.dtos.CustomizationDTO;
import com.rsa.bingo.domain.models.Color;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardRestController {

    private final WebCardService cardService;

    public CardRestController(WebCardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/build/{values}/{primary}/{secondary}")
    public byte[] build(@PathVariable String values, @PathVariable String primary, @PathVariable String secondary) {
        var card = new CardDTO(null, values, null, null);
        var customization = new CustomizationDTO(null, Color.valueOf(primary), Color.valueOf(secondary));
        return cardService.toBytes(card, customization);
    }
}
