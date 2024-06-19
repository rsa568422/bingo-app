package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.app.application.services.CardService;
import com.rsa.bingo.app.application.services.ColorsService;
import com.rsa.bingo.app.infrastructure.dtos.CardDTO;
import com.rsa.bingo.domain.models.Colors;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/cards")
public class CardController {

    private static final String CARDS = "cards";
    private static final String CARD = "card";
    private static final String CARD_DTO = "cardDTO";
    private static final String COLOR = "color";
    private static final String COLORS = "colors";
    private static final Colors DEFAULT_COLOR = new Colors(0, "rgb(0,0,0)", "rgb(128,128,128)");

    private final CardService cardService;

    private final ColorsService colorsService;

    public CardController(CardService cardService, ColorsService colorsService) {
        this.cardService = cardService;
        this.colorsService = colorsService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute(CARDS, IterableUtils.toList(cardService.getAll()));
        return "/cards/list";
    }

    @GetMapping("/player/{playerId}")
    public String findByPlayerId(@PathVariable Integer playerId, Model model) {
        model.addAttribute(CARDS, IterableUtils.toList(cardService.getByPlayerId(playerId)));
        return "/cards/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        var card = cardService.get(id).orElseThrow(() -> new VerifyError("Cartón no encontrado"));
        var colors = IterableUtils.toList(colorsService.getByCardId(id));
        colors.add(DEFAULT_COLOR);
        model.addAttribute(CARD, card);
        model.addAttribute(COLOR, DEFAULT_COLOR);
        model.addAttribute(COLORS, colors.stream().sorted(Comparator.comparing(Colors::getId)).toList());
        return "/cards/view";
    }

    @GetMapping("/color/{id}/{colorId}")
    public String color(@PathVariable Integer id, @PathVariable Integer colorId, Model model) {
        var card = cardService.get(id).orElseThrow(() -> new VerifyError("Cartón no encontrado"));
        var colors = IterableUtils.toList(colorsService.getByCardId(id));
        colors.add(DEFAULT_COLOR);
        model.addAttribute(CARD, card);
        model.addAttribute(COLOR, colorsService.get(colorId).orElseThrow(() -> new VerifyError("Color no encontrado")));
        model.addAttribute(COLORS, colors.stream().sorted(Comparator.comparing(Colors::getId)).toList());
        return "/cards/view";
    }

    @GetMapping("/builder")
    public String builder(Model model) {
        model.addAttribute(CARD_DTO, new CardDTO());
        return "/cards/builder";
    }
}
