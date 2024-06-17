package com.rsa.bingo.app.infrastructure.controllers;

import com.rsa.bingo.app.application.services.CardService;
import com.rsa.bingo.domain.models.Card;
import com.rsa.bingo.domain.models.Colors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("card")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    public void get() throws IOException {
        Files.write(Path.of("./src/main/resources").resolve("temp.xls"), service.toBytes(CARD, COLORS));
    }

    private static final Integer[][] VALUES = new Integer[][] {
        {null, 18, 24, null, null, 54, 67, null, 80},
        {null, null, 26, null, 41, 55, null, 70, 87},
        {4, null, null, 39, 42, null, 69, 72, null}
    };
    private static final Card CARD = new Card(VALUES);
    private static final Colors COLORS = new Colors("rgb(0,128,0)", "rgb(0,255,0)");
}
