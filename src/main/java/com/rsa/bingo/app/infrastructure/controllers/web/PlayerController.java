package com.rsa.bingo.app.infrastructure.controllers.web;

import com.rsa.bingo.app.application.services.WebCardService;
import com.rsa.bingo.app.application.services.WebPlayerService;
import com.rsa.bingo.app.infrastructure.dtos.PlayerDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.rsa.bingo.app.infrastructure.Constants.*;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private final WebPlayerService playerService;

    private final WebCardService cardService;

    public PlayerController(WebPlayerService playerService, WebCardService cardService) {
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
        model.addAttribute(CUSTOMIZATION, defaultCustomization(null));
        return "players/view";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute(PLAYER, new PlayerDTO());
        return "players/create";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        playerService.delete(id);
        return "redirect:/player";
    }

    @PostMapping
    public String save(PlayerDTO player) {
        player = playerService.save(player);
        return String.format("redirect:/player/%d", player.getId());
    }
}
