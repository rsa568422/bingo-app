package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.domain.services.CardService;
import com.rsa.bingo.domain.services.ColorsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.rsa.bingo.app.infrastructure.Constants.CARD;
import static com.rsa.bingo.app.infrastructure.Constants.COLOR;
import static com.rsa.bingo.app.infrastructure.Constants.COLORS;
import static com.rsa.bingo.app.infrastructure.Constants.DEFAULT_COLOR;

@Controller
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    private final ColorsService colorsService;

    public CardController(CardService cardService, ColorsService colorsService) {
        this.cardService = cardService;
        this.colorsService = colorsService;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model) {
        model.addAttribute(CARD, cardService.findById(id).orElseThrow(() -> new VerifyError("Card not found")));
        model.addAttribute(COLORS, colorsService.findByCardId(id));
        model.addAttribute(COLOR, DEFAULT_COLOR);
        return "cards/view";
    }

    @GetMapping("/builder")
    public String builder(Model model) {
        model.addAttribute(CARD, new CardDTO());
        model.addAttribute(COLOR, DEFAULT_COLOR);
        return "cards/builder";
    }
}
