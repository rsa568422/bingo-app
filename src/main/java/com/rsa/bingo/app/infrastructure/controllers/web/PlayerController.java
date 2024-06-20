package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.domain.services.CardService;
import com.rsa.bingo.domain.services.PlayerService;
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

    private final PlayerService playerService;

    private final CardService cardService;

    public PlayerController(PlayerService playerService, CardService cardService) {
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
        var player = playerService.findById(id).orElseThrow(() -> new VerifyError("Player not found"));
        model.addAttribute(PLAYER, player);
        model.addAttribute(CARDS, cardService.findByPlayerId(player.getId()));
        return "players/view";
    }
}
