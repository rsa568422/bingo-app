package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.app.application.services.DtoCardService;
import com.rsa.bingo.app.application.services.DtoPlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.rsa.bingo.app.infrastructure.Constants.CARDS;
import static com.rsa.bingo.app.infrastructure.Constants.PLAYER;
import static com.rsa.bingo.app.infrastructure.Constants.PLAYERS;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private final DtoPlayerService playerService;

    private final DtoCardService cardService;

    public PlayerController(DtoPlayerService playerService, DtoCardService cardService) {
        this.playerService = playerService;
        this.cardService = cardService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute(PLAYERS, playerService.findAll());
        return "players/list";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id, Model model) {
        model.addAttribute(PLAYER, playerService.findById(id));
        model.addAttribute(CARDS, cardService.findByPlayerId(id));
        return "players/view";
    }
}
